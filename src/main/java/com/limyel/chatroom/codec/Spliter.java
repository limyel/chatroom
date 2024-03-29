package com.limyel.chatroom.codec;

import com.limyel.chatroom.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 分包
 * 需要维护每个 Channel 读到的数据，有状态，所以不能用单例模式
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    public static final int LENGTH_FIELD_OFFSET = 7;
    public static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // todo 应该先解码再校验魔数？避免半包导致魔数不完整
        // 校验魔数，是否是该程序的数据包
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
