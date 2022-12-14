package com.limyel.chatroom.codec;

import com.limyel.chatroom.protocol.AbstractPacket;
import com.limyel.chatroom.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author limyel
 */
@Lazy
@Component
public class PacketEncoder extends MessageToByteEncoder<AbstractPacket> {

    @Autowired
    private PacketCodec packetCodec;

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, AbstractPacket packet, ByteBuf byteBuf) throws Exception {
        packetCodec.encode(byteBuf, packet);
    }
}
