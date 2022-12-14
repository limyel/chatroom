package com.limyel.chatroom.server;

import com.limyel.chatroom.codec.PacketCodecHandler;
import com.limyel.chatroom.codec.PacketDecoder;
import com.limyel.chatroom.codec.PacketEncoder;
import com.limyel.chatroom.codec.Spliter;
import com.limyel.chatroom.config.ChatroomConfig;
import com.limyel.chatroom.handler.IMIdleStateHandler;
import com.limyel.chatroom.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;

@Component
public class Server {

    private static final int PORT = 8000;

    @Autowired
    private PacketCodecHandler packetCodecHandler;

    @Autowired
    private LoginRequestHandler loginRequestHandler;

    @Autowired
    private HeartBeatRequestHandler heartBeatRequestHandler;

    @Autowired
    private AuthHandler authHandler;

    @Autowired
    private IMHandler imHandler;

    private ServerBootstrap serverBootstrap;

    @PostConstruct
    public void init() {
        serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                ChannelPipeline pipeline = socketChannel.pipeline();
                                pipeline.addLast(new IMIdleStateHandler());
                                pipeline.addLast(new Spliter());
                                pipeline.addLast(packetCodecHandler);
                                pipeline.addLast(loginRequestHandler);
                                pipeline.addLast(heartBeatRequestHandler);
                                pipeline.addLast(authHandler);
                                pipeline.addLast(imHandler);
                            }
                        });
                    }
                });
    }

    public void start() {
        bind(serverBootstrap, PORT);
    }

    /**
     * 绑定端口，自动递增端口号
     * @param serverBootstrap
     * @param port
     */
    private void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功！");
            } else {
                System.err.println("端口[" + port + "]绑定失败，尝试下一个端口。");
                bind(serverBootstrap, port + 1);
            }
        });
    }

}
