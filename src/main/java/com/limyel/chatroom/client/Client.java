package com.limyel.chatroom.client;

import com.limyel.chatroom.client.console.ConsoleCommandManager;
import com.limyel.chatroom.client.console.LoginConsoleCommand;
import com.limyel.chatroom.client.handler.*;
import com.limyel.chatroom.codec.PacketDecoder;
import com.limyel.chatroom.codec.PacketEncoder;
import com.limyel.chatroom.codec.Spliter;
import com.limyel.chatroom.handler.IMIdleStateHandler;
import com.limyel.chatroom.protocol.request.LoginRequestPacket;
import com.limyel.chatroom.protocol.request.MessageRequestPacket;
import com.limyel.chatroom.protocol.response.QuitGroupResponsePacket;
import com.limyel.chatroom.server.handler.QuitGroupRequestHandler;
import com.limyel.chatroom.utils.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author limyel
 */
@Component
public class Client {

    private final int MAX_RETRY = 5;

    private final String HOST = "127.0.0.1";

    private final int PORT = 8000;

    private Bootstrap bootstrap;

    @Autowired
    private PacketDecoder packetDecoder;

    @Autowired
    private LoginResponseHandler loginResponseHandler;

    @Autowired
    private MessageResponseHandler messageResponseHandler;

    @Autowired
    private CreateGroupResponseHandler createGroupResponseHandler;

    @Autowired
    private JoinGroupResponseHandler joinGroupResponseHandler;

    @Autowired
    private QuitGroupResponseHandler quitGroupResponseHandler;

    @Autowired
    private ListGroupMembersResponseHandler listGroupMembersResponseHandler;

    @Autowired
    private LogoutResponseHandler logoutResponseHandler;

    @Autowired
    private GroupMessageResponseHandler groupMessageResponseHandler;

    @Autowired
    private PacketEncoder packetEncoder;

    @Autowired
    private HeartBeatTimerHandler heartBeatTimerHandler;

    @PostConstruct
    public void init() throws InterruptedException {
        bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new IMIdleStateHandler());
                        socketChannel.pipeline().addLast(new Spliter());
                        socketChannel.pipeline().addLast(packetDecoder);
                        socketChannel.pipeline().addLast(loginResponseHandler);
                        socketChannel.pipeline().addLast(messageResponseHandler);
                        // ???????????????????????????
                        socketChannel.pipeline().addLast(createGroupResponseHandler);
                        // ?????????????????????
                        socketChannel.pipeline().addLast(joinGroupResponseHandler);
                        // ?????????????????????
                        socketChannel.pipeline().addLast(quitGroupResponseHandler);
                        // ??????????????????????????????
                        socketChannel.pipeline().addLast(listGroupMembersResponseHandler);
                        // ????????????????????????
                        socketChannel.pipeline().addLast(groupMessageResponseHandler);
                        // ?????????????????????
                        socketChannel.pipeline().addLast(logoutResponseHandler);
                        socketChannel.pipeline().addLast(packetEncoder);
                        socketChannel.pipeline().addLast(heartBeatTimerHandler);
                    }
                });
    }

    public void start() {
        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                Channel channel = ((ChannelFuture) future).channel();
                // ????????????????????????????????????
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.err.println("???????????????????????????????????????");
            } else {
                // ????????????
                int order = MAX_RETRY - retry + 1;
                // ??????????????????
                int delay = 1 << order;
                System.err.println(new Date() + "?????????????????????" + order + "?????????...");
                // ?????? BootstrapConfig
                bootstrap.config()
                        // ?????????????????? group
                        .group()
                        // ??????????????????
                        .schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }
}
