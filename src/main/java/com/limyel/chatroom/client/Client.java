package com.limyel.chatroom.client;

import com.limyel.chatroom.client.handler.LoginResponseHandler;
import com.limyel.chatroom.client.handler.MsgResponseHandler;
import com.limyel.chatroom.codec.PacketDecoder;
import com.limyel.chatroom.codec.PacketEncoder;
import com.limyel.chatroom.codec.Spliter;
import com.limyel.chatroom.protocol.PacketCodeC;
import com.limyel.chatroom.protocol.request.LoginRequestPacket;
import com.limyel.chatroom.protocol.request.MsgRequestPacket;
import com.limyel.chatroom.session.Session;
import com.limyel.chatroom.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 指定线程模型
                .group(worker)
                // 指定 IO 模型
                .channel(NioSocketChannel.class)
                // 读写处理
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new Spliter());
                        nioSocketChannel.pipeline().addLast(new PacketDecoder());
                        nioSocketChannel.pipeline().addLast(new LoginResponseHandler());
                        nioSocketChannel.pipeline().addLast(new MsgResponseHandler());
                        nioSocketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });

        connect(bootstrap, "localhost", 8090, 5);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接服务端成功");
                // 连接成功，获取当前 Channel
                Channel channel = ((ChannelFuture) future).channel();
                // 启动控制台线程
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.out.println("重试次数耗尽，连接服务端失败");
            } else {
                // Bootstrap 配置信息的封装
                bootstrap.config()
                        .group().schedule(() -> connect(bootstrap, host, port, retry - 1), 5, TimeUnit.SECONDS);
            }
        });
    }

    /**
     * 控制台线程，输入消息
     * @param channel
     */
    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                Scanner scanner = new Scanner(System.in);
                if (SessionUtil.hasLogin(channel)) {
                    // 已登录
                    String toUuid = scanner.nextLine();
                    String msg = scanner.nextLine();

                    MsgRequestPacket msgRequestPacket = new MsgRequestPacket();
                    msgRequestPacket.setMsg(msg);
                    msgRequestPacket.setToUuid(toUuid);

                    ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), msgRequestPacket);
                    channel.writeAndFlush(byteBuf);
                } else {
                    System.out.println("请输入用户名：");
                    String username = scanner.nextLine();
                    System.out.println("请输入密码：");
                    String password = scanner.nextLine();

                    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
                    loginRequestPacket.setUsername(username);
                    loginRequestPacket.setPassword(password);

                    channel.writeAndFlush(loginRequestPacket);
                    // 停一秒，等待消息
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
