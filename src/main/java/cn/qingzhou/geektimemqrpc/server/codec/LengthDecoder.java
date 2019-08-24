package cn.qingzhou.geektimemqrpc.server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 长度解析器
 * @author qingzhou
 * 2019-08-24
 */
public class LengthDecoder extends LengthFieldBasedFrameDecoder {

    public static final int MAX_FRAME_LENGTH = 32;
    public static final int LENGTH_FIELD_OFFSET = 0;
    public static final int LENGTH_FIELD_LENGTH = 1;

    public LengthDecoder() {
        super(MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        return super.decode(ctx, in);
    }
}
