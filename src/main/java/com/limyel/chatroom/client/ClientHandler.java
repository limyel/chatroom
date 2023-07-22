package com.limyel.chatroom.client;

import com.limyel.chatroom.protocol.Packet;
import com.limyel.chatroom.protocol.PacketCodeC;
import com.limyel.chatroom.protocol.command.Command;
import com.limyel.chatroom.protocol.request.LoginRequestPacket;
import com.limyel.chatroom.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;
import java.util.Date;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(LocalDateTime.now() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUsername("limyel");
        packet.setPassword("123456");

        // 编码
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), packet);

        // 写数据
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet.getCommand().equals(Command.LOGIN_RESPONSE)) {
            var responsePacket = (LoginResponsePacket) packet;

            if (responsePacket.getSuccess()) {
                System.out.println(LocalDateTime.now() + "：客户端登录成功");
            } else {
                System.out.println(LocalDateTime.now() + "：客户端登录失败，原因：" + responsePacket.getReason());
            }
        }
    }
}
