package com.limyel.chatroom.server.handler;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<AbstractPacket> {

    public Map<Byte, SimpleChannelInboundHandler<? extends AbstractPacket>> handlerMap;

    @Autowired
    private MessageRequestHandler messageRequestHandler;

    @Autowired
    private CreateGroupRequestHandler createGroupRequestHandler;

    @Autowired
    private JoinGroupRequestHandler joinGroupRequestHandler;

    @Autowired
    private QuitGroupRequestHandler quitGroupRequestHandler;

    @Autowired
    private ListGroupMembersRequestHandler listGroupMembersRequestHandler;

    @Autowired
    private GroupMessageRequestHandler groupMessageRequestHandler;

    @Autowired
    private LogoutRequestHandler logoutRequestHandler;

    @PostConstruct
    public void init() {
        handlerMap = new HashMap<>();

        handlerMap.put(CommandConstant.MESSAGE_REQUEST, messageRequestHandler);
        handlerMap.put(CommandConstant.CREATE_GROUP_REQUEST, createGroupRequestHandler);
        handlerMap.put(CommandConstant.JOIN_GROUP_REQUEST, joinGroupRequestHandler);
        handlerMap.put(CommandConstant.QUIT_GROUP_REQUEST, quitGroupRequestHandler);
        handlerMap.put(CommandConstant.LIST_GROUP_MEMBERS_REQUEST, listGroupMembersRequestHandler);
        handlerMap.put(CommandConstant.GROUP_MESSAGE_REQUEST, groupMessageRequestHandler);
        handlerMap.put(CommandConstant.LOGOUT_REQUEST, logoutRequestHandler);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractPacket packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
