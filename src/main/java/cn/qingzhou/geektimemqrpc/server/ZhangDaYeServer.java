package cn.qingzhou.geektimemqrpc.server;

import cn.qingzhou.geektimemqrpc.helper.NamedThreadFactory;
import cn.qingzhou.geektimemqrpc.server.codec.MsgDecoder;
import cn.qingzhou.geektimemqrpc.server.codec.MsgEncoder;
import cn.qingzhou.geektimemqrpc.server.codec.PacketDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 张大爷
 *
 * @author qingzhou
 * 2019-08-24
 */
@Slf4j
@Component
public class ZhangDaYeServer {

    public void start() {
        int port = 8080;
        EventLoopGroup bossGroup = new NioEventLoopGroup(1, new NamedThreadFactory("blg-boss"));
        EventLoopGroup workerGroup = new NioEventLoopGroup(1, new NamedThreadFactory("blg-worker"));

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
//                .childOption(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(65535))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new PacketDecoder());

                        ch.pipeline().addLast(new MsgDecoder());
                        ch.pipeline().addLast(MsgEncoder.INSTANCE);

                        ch.pipeline().addLast(ZhangDaYeMsgHandler.INSTANCE);
                    }
                });
        b.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("zhangdaye server startup success");
            } else {
                log.error("zhangdaye server startup failed", future.cause());
            }
        });
    }

}
