package com.limyel.chatroom.client.handler;

import com.limyel.chatroom.protocol.PacketCodeC;
import com.limyel.chatroom.protocol.request.LoginRequestPacket;
import com.limyel.chatroom.protocol.response.LoginResponsePacket;
import com.limyel.chatroom.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(LocalDateTime.now() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUsername("limyel");
        packet.setPassword("123456");

        // 写数据
        ctx.channel().writeAndFlush(packet);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        if (loginResponsePacket.getSuccess()) {
            // 标记为已登录
            LoginUtil.markAsLogin(channelHandlerContext.channel());
            System.out.println(LocalDateTime.now() + "：客户端登录成功");
        } else {
            System.out.println(LocalDateTime.now() + "：客户端登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

}
