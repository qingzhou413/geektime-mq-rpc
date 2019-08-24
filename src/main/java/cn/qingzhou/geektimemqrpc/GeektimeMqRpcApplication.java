package cn.qingzhou.geektimemqrpc;

import cn.qingzhou.geektimemqrpc.client.LiDaYeClient;
import cn.qingzhou.geektimemqrpc.server.ZhangDaYeServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class GeektimeMqRpcApplication implements ApplicationRunner {

    @Resource
    private ZhangDaYeServer zhangDaYeServer;
    @Resource
    private LiDaYeClient liDaYeClient;

    public static void main(String[] args) {
        SpringApplication.run(GeektimeMqRpcApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        zhangDaYeServer.start();
        liDaYeClient.start();
    }
}
