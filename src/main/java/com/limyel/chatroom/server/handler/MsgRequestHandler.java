package com.limyel.chatroom.server.handler;

import com.limyel.chatroom.protocol.PacketCodeC;
import com.limyel.chatroom.protocol.request.MsgRequestPacket;
import com.limyel.chatroom.protocol.response.MsgResponsePacket;
import com.limyel.chatroom.session.Session;
import com.limyel.chatroom.util.SessionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class MsgRequestHandler extends SimpleChannelInboundHandler<MsgRequestPacket> {

    public static final MsgRequestHandler INSTANCE = new MsgRequestHandler();

    private MsgRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgRequestPacket msgRequestPacket) throws Exception {
        // 发送方的 Session
        Session session = SessionUtil.getSession(channelHandlerContext.channel());

        // 响应包
        MsgResponsePacket responsePacket = new MsgResponsePacket();
        responsePacket.setFromUuid(session.getUuid());
        responsePacket.setFromUsername(session.getUsername());
        responsePacket.setMsg(msgRequestPacket.getMsg());

        // 接收方的 Channel
        Channel toChannel = SessionUtil.getChannel(msgRequestPacket.getToUuid());

        // 发送消息到接收方
        if (SessionUtil.hasLogin(toChannel)) {
            toChannel.writeAndFlush(responsePacket);
        } else {
            // todo 离线消息
            System.err.println("[" + msgRequestPacket.getToUuid() + "] 不在线，发送失败");
        }

    }
}
