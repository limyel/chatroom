package com.limyel.chatroom.client.handler;

import com.limyel.chatroom.protocol.response.LogoutResponsePacket;
import com.limyel.chatroom.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutResponsePacket logoutResponsePacket) throws Exception {
        SessionUtil.unBindSession(channelHandlerContext.channel());
    }
}
