package cn.qingzhou.geektimemqrpc.server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 包解析器
 *
 * @author qingzhou
 * 2019-08-24
 */
public class PacketDecoder extends ByteToMessageDecoder {


    private static LengthDecoder lengthDecoder = new LengthDecoder();

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int len = in.readableBytes();
        if (len <= LengthDecoder.LENGTH_FIELD_LENGTH) {
            return;
        }

        Object decode = lengthDecoder.decode(ctx, in);
        if (decode != null) {
            out.add(decode);
        }
    }
}
