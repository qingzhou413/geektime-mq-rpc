package cn.qingzhou.geektimemqrpc.server.codec;

import cn.qingzhou.geektimemqrpc.msg.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 消息解析器
 *
 * @author qingzhou
 * 2019-08-24
 */
public class MsgDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {

        int len = in.readByte() & 0xff;

        byte[] data = new byte[len];
        in.readBytes(data);

        Message message = new Message(new String(data, Charset.forName("UTF-8")));
        out.add(message);
    }
}
