package com.limyel.chatroom.server.handler;

import com.limyel.chatroom.protocol.AbstractPacket;
import com.limyel.chatroom.protocol.PacketCodec;
import com.limyel.chatroom.protocol.request.LoginRequestPacket;
import com.limyel.chatroom.protocol.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author limyel
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        // 解码
        AbstractPacket packet = PacketCodec.INSTANCE.decode(buf);

        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
            } else {
                // todo 统一的 ErrorCode
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
            }
            ByteBuf response = PacketCodec.INSTANCE.encode(loginResponsePacket);
            ctx.channel().writeAndFlush(response);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
