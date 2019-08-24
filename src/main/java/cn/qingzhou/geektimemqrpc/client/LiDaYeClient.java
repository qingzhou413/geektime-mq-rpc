package cn.qingzhou.geektimemqrpc.client;

import cn.qingzhou.geektimemqrpc.server.codec.MsgDecoder;
import cn.qingzhou.geektimemqrpc.server.codec.MsgEncoder;
import cn.qingzhou.geektimemqrpc.server.codec.PacketDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 李大爷客户端
 *
 * @author qingzhou
 * 2019-08-24
 */
@Slf4j
@Component
public class LiDaYeClient {

    public void start() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(1);

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
//                .option(ChannelOption.TCP_NODELAY, true)
                // 1.指定线程模型
                .group(workerGroup)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new PacketDecoder());

                        ch.pipeline().addLast(new MsgDecoder());
                        ch.pipeline().addLast(MsgEncoder.INSTANCE);

                        ch.pipeline().addLast(LiDaYeMsgHandler.INSTANCE);
                    }
                });
        // 4.建立连接
        bootstrap.connect("127.0.0.1", 8080).addListener(future -> {
            if (future.isSuccess()) {
                log.info("连接已建立");
            } else {
                log.error("连接建立失败!");
            }
        });
    }
}
