package com.limyel.chatroom.codec;

import com.limyel.chatroom.protocol.Packet;
import com.limyel.chatroom.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 其实是一个 ChannelInboundHandlerAdapter，将 ByteBuf 解码为 Java 对象
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketCodeC.INSTANCE.decode(byteBuf));
    }
}
