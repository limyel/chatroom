package com.limyel.chatroom.server.handler;

import com.limyel.chatroom.protocol.PacketCodeC;
import com.limyel.chatroom.protocol.request.LoginRequestPacket;
import com.limyel.chatroom.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(loginRequestPacket.getVersion());
        // 校验登录
        if (valid(loginRequestPacket)) {
            responsePacket.setSuccess(true);
        }

        channelHandlerContext.channel().writeAndFlush(responsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
