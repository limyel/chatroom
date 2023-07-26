package com.limyel.chatroom.client.handler;

import com.limyel.chatroom.protocol.response.MsgResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class MsgResponseHandler extends SimpleChannelInboundHandler<MsgResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgResponsePacket msgResponsePacket) throws Exception {
        System.out.println(LocalDateTime.now() + "：收到服务端消息：" + msgResponsePacket.getMsg());
    }
}
