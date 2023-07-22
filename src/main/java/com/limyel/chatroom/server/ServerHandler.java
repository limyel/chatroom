package com.limyel.chatroom.server;

import com.limyel.chatroom.protocol.Packet;
import com.limyel.chatroom.protocol.PacketCodeC;
import com.limyel.chatroom.protocol.command.Command;
import com.limyel.chatroom.protocol.request.LoginRequestPacket;
import com.limyel.chatroom.protocol.request.MsgRequestPacket;
import com.limyel.chatroom.protocol.response.LoginResponsePacket;
import com.limyel.chatroom.protocol.response.MsgResponsePacket;
import com.limyel.chatroom.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.time.LocalDateTime;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        ByteBuf response = null;
        if (packet.getCommand().equals(Command.LOGIN_REQUEST)) {
            LoginRequestPacket requestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket responsePacket = new LoginResponsePacket();
            responsePacket.setVersion(requestPacket.getVersion());
            // 校验登录
            if (valid(requestPacket)) {
                responsePacket.setSuccess(true);

            }

            // 编码
            response = PacketCodeC.INSTANCE.encode(ctx.alloc(), responsePacket);
        } else if (packet.getCommand().equals(Command.MSG_REQUEST)) {
            MsgRequestPacket requestPacket = (MsgRequestPacket) packet;
            System.out.println(LocalDateTime.now() + "：收到客户端消息：" + requestPacket.getMsg());

            MsgResponsePacket responsePacket = new MsgResponsePacket();
            responsePacket.setMsg("服务端回复：" + requestPacket.getMsg());
            response = PacketCodeC.INSTANCE.encode(ctx.alloc(), responsePacket);
        }

        ctx.channel().writeAndFlush(response);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
