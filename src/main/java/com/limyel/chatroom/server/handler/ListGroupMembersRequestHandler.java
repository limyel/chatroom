package com.limyel.chatroom.server.handler;

import com.limyel.chatroom.protocol.request.ListGroupMembersRequestPacket;
import com.limyel.chatroom.protocol.response.ListGroupMembersResponsePacket;
import com.limyel.chatroom.session.Session;
import com.limyel.chatroom.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    private ListGroupMembersRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        String groupId = listGroupMembersRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        if (channelGroup != null) {
            List<String> usernameList = new ArrayList<>();
            for (Channel channel : channelGroup) {
                Session session = SessionUtil.getSession(channel);
                usernameList.add(session.getUsername());
            }

            ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
            listGroupMembersResponsePacket.setGroupId(groupId);
            listGroupMembersResponsePacket.setUsernameList(usernameList);

            channelHandlerContext.channel().writeAndFlush(listGroupMembersResponsePacket);
        }
    }
}
