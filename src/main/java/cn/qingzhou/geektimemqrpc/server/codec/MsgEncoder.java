package cn.qingzhou.geektimemqrpc.server.codec;

import cn.qingzhou.geektimemqrpc.helper.CharsetHelper;
import cn.qingzhou.geektimemqrpc.msg.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

/**
 * 消息编码器
 *
 * @author qingzhou
 * 2019-08-24
 */
@ChannelHandler.Sharable
public class MsgEncoder extends MessageToByteEncoder<Message> {
    public static final MsgEncoder INSTANCE = new MsgEncoder();

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf out) throws Exception {
        if (message == null) {
            throw new RuntimeException("encode msg null");
        }
        byte[] bytes = message.getMsg().getBytes(CharsetHelper.UTF8);
        int len = bytes.length;
        out.writeByte(len);
        out.writeBytes(bytes);
    }
}
