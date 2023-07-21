package com.limyel.chatroom;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

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
                        nioSocketChannel.pipeline().addLast(new FirstServerHandler());
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
