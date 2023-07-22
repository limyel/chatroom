package com.limyel.chatroom.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Server {
    public static void main(String[] args) {
        // 创建两个线程池
        // 监听端口
        NioEventLoopGroup boss = new NioEventLoopGroup();
        // 客户端连接
        NioEventLoopGroup worker = new NioEventLoopGroup();

        // 引导类，负责服务端的启动
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                // 指定线程模型
                .group(boss, worker)
                // 指定 IO 模型
                .channel(NioServerSocketChannel.class)
                // 读写处理
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        // 该连接的处理逻辑链，责任链模式
                        nioSocketChannel.pipeline().addLast(new ServerHandler());
                    }
                });

        bind(bootstrap, 8080);
    }

    private static void bind(ServerBootstrap bootstrap, int port) {
        bootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口绑定成功 " + port);
            } else {
                System.out.println("端口绑定失败 " + port);
                bind(bootstrap, port + 1);
            }
        });
    }
}