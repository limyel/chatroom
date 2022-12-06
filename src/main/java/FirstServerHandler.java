import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.readerIndex());

        System.out.println(new Date() + ": 服务端读到数据 -> " + buf.toString(StandardCharsets.UTF_8));

        System.out.println(new Date() + "：服务端写出数据");

        ByteBuf out = getByteBuf(ctx);

        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // 获取二进制抽象 ByteBuf
        ByteBuf buf = ctx.alloc().buffer();

        // 准备数据
        byte[] bytes = "你也好，limyel".getBytes(StandardCharsets.UTF_8);

        // 填充数据到 ByteBuf
        buf.writeBytes(bytes);

        return buf;
    }
}
