package com.limyel.chatroom.codec;

import com.limyel.chatroom.protocol.AbstractPacket;
import com.limyel.chatroom.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, AbstractPacket> {

    @Autowired
    private PacketCodec packetCodec;

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, AbstractPacket packet, List<Object> list) throws Exception {
        ByteBuf buf = channelHandlerContext.channel().alloc().ioBuffer();
        packetCodec.encode(buf, packet);
        list.add(buf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf buf, List<Object> list) throws Exception {
        list.add(packetCodec.decode(buf));
    }
}
