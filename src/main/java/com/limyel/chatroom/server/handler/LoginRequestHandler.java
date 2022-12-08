package com.limyel.chatroom.server.handler;

import com.limyel.chatroom.protocol.request.LoginRequestPacket;
import com.limyel.chatroom.protocol.response.LoginResponsePacket;
import com.limyel.chatroom.session.Session;
import com.limyel.chatroom.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author limyel
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUsername(loginRequestPacket.getUsername());

        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            // todo 数据库
            Long userId = System.currentTimeMillis();
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + new Date() + " " + loginRequestPacket.getUsername() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), channelHandlerContext.channel());
        } else {
            // todo 统一的 ErrorCode
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println("[" + new Date() + " " + loginRequestPacket.getUsername() + "]登录失败");
        }
        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
