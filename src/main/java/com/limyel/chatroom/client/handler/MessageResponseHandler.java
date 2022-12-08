package com.limyel.chatroom.client.handler;

import com.limyel.chatroom.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author limyel
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        Long fromUserId = messageResponsePacket.getFromUserId();
        String fromUsername = messageResponsePacket.getFromUsername();
        System.out.println(fromUserId + ":" + fromUsername + " -> " + messageResponsePacket.getMessage());
    }
}
