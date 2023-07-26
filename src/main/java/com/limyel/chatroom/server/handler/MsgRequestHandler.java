package com.limyel.chatroom.server.handler;

import com.limyel.chatroom.protocol.PacketCodeC;
import com.limyel.chatroom.protocol.request.MsgRequestPacket;
import com.limyel.chatroom.protocol.response.MsgResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class MsgRequestHandler extends SimpleChannelInboundHandler<MsgRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgRequestPacket msgRequestPacket) throws Exception {
        System.out.println(LocalDateTime.now() + "：收到客户端消息：" + msgRequestPacket.getMsg());

        MsgResponsePacket responsePacket = new MsgResponsePacket();
        responsePacket.setMsg("服务端回复：" + msgRequestPacket.getMsg());

        channelHandlerContext.channel().writeAndFlush(responsePacket);
    }
}
