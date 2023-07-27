package com.limyel.chatroom.client.handler;

import com.limyel.chatroom.protocol.request.LoginRequestPacket;
import com.limyel.chatroom.protocol.response.LoginResponsePacket;
import com.limyel.chatroom.session.Session;
import com.limyel.chatroom.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.getSuccess()) {
            // 标记为已登录
            SessionUtil.bindSession(new Session(loginResponsePacket.getUuid(), loginResponsePacket.getUsername()),
                    channelHandlerContext.channel());
            System.out.println(LocalDateTime.now() + "：客户端登录成功");
        } else {
            System.out.println(LocalDateTime.now() + "：客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

}
