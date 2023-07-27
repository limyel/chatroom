package com.limyel.chatroom.server.handler;

import com.limyel.chatroom.protocol.request.JoinGroupRequestPacket;
import com.limyel.chatroom.protocol.response.JoinGroupResponsePacket;
import com.limyel.chatroom.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        String groupId = joinGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        if (channelGroup != null) {
            channelGroup.add(channelHandlerContext.channel());

            JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
            joinGroupResponsePacket.setSuccess(true);
            joinGroupResponsePacket.setGroupId(groupId);

            channelHandlerContext.channel().writeAndFlush(joinGroupResponsePacket);
        }
    }
}
