package cn.qingzhou.geektimemqrpc.client;

import cn.qingzhou.geektimemqrpc.helper.Common;
import cn.qingzhou.geektimemqrpc.helper.TimeHelper;
import cn.qingzhou.geektimemqrpc.msg.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 李大爷收到消息处理器
 *
 * @author qingzhou
 * 2019-08-24
 */
@Slf4j
@ChannelHandler.Sharable
public class LiDaYeMsgHandler extends SimpleChannelInboundHandler<Message> {

    public static final LiDaYeMsgHandler INSTANCE = new LiDaYeMsgHandler();

    private final String z0 = "吃了没，您吶?";
    private final String l1 = "刚吃。";
    private final String l2 = "您这，嘛去？";
    private final String z3 = "嗨！吃饱了溜溜弯儿。";
    private final String l4 = "有空家里坐坐啊。";
    private final String z5 = "回头去给老太太请安！";

    //worker只有1个线程，不需要原子类
    private int cnt1 = 0;
    private int cnt2 = 0;
    private int cnt3 = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
//        new Thread(() -> {
//            while (true) {
//                log.info("cnt1 {}", cnt1);
//                log.info("cnt2 {}", cnt2);
//                log.info("cnt3 {}", cnt3);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("LiDaYeMsgHandler exceptionCaught:", cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
//        log.info("李大爷收到消息：{}", message.getMsg());
        if (z0.equals(message.getMsg())) {
            cnt1++;
            ctx.write(new Message(l1));
            ctx.write(new Message(l2));
            if (cnt1 % 100 == 0) {
                ctx.flush();
            }
        } else if (z3.equals(message.getMsg())) {
            cnt2++;
            ctx.write(new Message(l4));
            if (cnt2 % 100 == 0) {
                ctx.flush();
            }
        } else if (z5.equals(message.getMsg())) {
            cnt3++;
            if (cnt3 == Common.SEND_COUNT) {
                TimeHelper.end();
            }
        }
    }


}
