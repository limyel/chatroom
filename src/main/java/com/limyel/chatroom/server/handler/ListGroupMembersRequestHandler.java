package com.limyel.chatroom.server.handler;

import com.limyel.chatroom.protocol.request.ListGroupMembersRequestPacket;
import com.limyel.chatroom.protocol.response.ListGroupMembersResponsePacket;
import com.limyel.chatroom.session.Session;
import com.limyel.chatroom.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author limyel
 */
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    private ListGroupMembersRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        Long groupId = listGroupMembersRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        List<Session> sessionList = channelGroup.stream()
                .map(SessionUtil::getSession)
                .collect(Collectors.toList());

        ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
        listGroupMembersResponsePacket.setGroupId(groupId);
        listGroupMembersResponsePacket.setSessionList(sessionList);
        ctx.channel().writeAndFlush(listGroupMembersResponsePacket);
    }
}
