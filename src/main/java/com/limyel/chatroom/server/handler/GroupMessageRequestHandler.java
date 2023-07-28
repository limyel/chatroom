package com.limyel.chatroom.server.handler;

import com.limyel.chatroom.protocol.request.GroupMsgRequestPacket;
import com.limyel.chatroom.protocol.response.GroupMsgResponsePacket;
import com.limyel.chatroom.session.Session;
import com.limyel.chatroom.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMsgRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    private GroupMessageRequestHandler() {

    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMsgRequestPacket groupMsgRequestPacket) throws Exception {
        String groupId = groupMsgRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        if (channelGroup != null) {
            Session session = SessionUtil.getSession(channelHandlerContext.channel());

            GroupMsgResponsePacket groupMsgResponsePacket = new GroupMsgResponsePacket();
            groupMsgResponsePacket.setFromGroupId(groupId);
            groupMsgResponsePacket.setFromUsername(session.getUsername());
            groupMsgResponsePacket.setMsg(groupMsgRequestPacket.getMsg());

            channelGroup.writeAndFlush(groupMsgResponsePacket);
        }
    }
}
