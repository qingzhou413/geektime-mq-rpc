package cn.qingzhou.geektimemqrpc.helper;

import lombok.extern.slf4j.Slf4j;

/**
 * 时间帮助类
 *
 * @author qingzhou
 * 2019-08-24
 */
@Slf4j
public class TimeHelper {

    static long start;
    static long end;

    public static void start() {
        start = System.currentTimeMillis();
    }

    public static void end() {
        end = System.currentTimeMillis();
        log.info("总计耗时 {}s", (end - start) / 1000.0);
    }


}
