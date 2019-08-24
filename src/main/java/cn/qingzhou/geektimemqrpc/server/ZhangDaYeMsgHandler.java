package cn.qingzhou.geektimemqrpc.server;

import cn.qingzhou.geektimemqrpc.helper.Common;
import cn.qingzhou.geektimemqrpc.helper.TimeHelper;
import cn.qingzhou.geektimemqrpc.msg.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author qingzhou
 * 2019-08-24
 */
@Slf4j
@ChannelHandler.Sharable
public class ZhangDaYeMsgHandler extends SimpleChannelInboundHandler<Message> {

    public static final ZhangDaYeMsgHandler INSTANCE = new ZhangDaYeMsgHandler();


    private final String z0 = "吃了没，您吶?";
    private final String l1 = "刚吃。";
    private final String l2 = "您这，嘛去？";
    private final String z3 = "嗨！吃饱了溜溜弯儿。";
    private final String l4 = "有空家里坐坐啊。";
    private final String z5 = "回头去给老太太请安！";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        TimeHelper.start();

        new Thread(() -> {
            //连接建立完成
            for (int i = 0; i < Common.SEND_COUNT; i++) {
                ctx.write(new Message(z0));
                if ((i + 1) % 100 == 0) {
                    ctx.flush();
                }
            }
        }).start();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("ZhangDaYeMsgHandler exceptionCaught:", cause);
        ctx.close();
    }

    private int cnt1 = 0;
    private int cnt2 = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
//        log.info("张大爷收到消息：{}", message.getMsg());
        if (l2.equals(message.getMsg())) {
            cnt1++;
            ctx.write(new Message(z3));
            if (cnt1 % 100 == 0) {
                ctx.flush();
            }
        } else if (l4.equals(message.getMsg())) {
            cnt2++;
            ctx.write(new Message(z5));
            if (cnt2 % 100 == 0) {
                ctx.flush();
            }
        }
    }
}
