package com.limyel.chatroom.codec;

import com.limyel.chatroom.protocol.AbstractPacket;
import com.limyel.chatroom.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author limyel
 */
@Component
public class PacketDecoder extends ByteToMessageDecoder {

    @Autowired
    private PacketCodec packetCodec;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        AbstractPacket decode = packetCodec.decode(byteBuf);
        list.add(decode);
    }
}
