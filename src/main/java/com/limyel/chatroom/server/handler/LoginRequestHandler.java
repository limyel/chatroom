package com.limyel.chatroom.server.handler;

import com.limyel.chatroom.protocol.request.LoginRequestPacket;
import com.limyel.chatroom.protocol.response.LoginResponsePacket;
import com.limyel.chatroom.session.Session;
import com.limyel.chatroom.util.IdUtil;
import com.limyel.chatroom.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        // 校验登录
        if (valid(loginRequestPacket)) {
            String username = loginRequestPacket.getUsername();
            String uuid = IdUtil.getUuid();

            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setUsername(username);
            loginResponsePacket.setUuid(uuid);

            System.out.println(LocalDateTime.now() + " " + uuid + " " + username + " 登录成功");
            SessionUtil.bindSession(new Session(uuid, username), channelHandlerContext.channel());
        }

        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

}
