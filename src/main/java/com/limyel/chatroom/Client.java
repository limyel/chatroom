package com.limyel.chatroom;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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
                        // 该连接的处理逻辑链，责任链模式
                        nioSocketChannel.pipeline().addLast(new FirstClientHandler());
                    }
                });

        connect(bootstrap, "localhost", 8080, 5);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接服务端成功");
            } else if (retry == 0) {
                System.out.println("重试次数耗尽，连接服务端失败");
            } else {
                // Bootstrap 配置信息的封装
                bootstrap.config()
                        .group().schedule(() -> connect(bootstrap, host, port, retry - 1), 5, TimeUnit.SECONDS);
            }
        });
    }
}
