package org.liuxinyi.demos.multiThread.join;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * sleep 不会释放锁
 * Created by Eric.Liu on 2016/11/9.
 */
@Slf4j
public class JoinDemo {

    private static volatile boolean stop = false;

    @Test
    public void test() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("thread begin ...");
                    Thread.sleep(5000);
                    stop = true;
                    log.info("thread end ...");
                } catch (Exception e) {
                    log.error("Exception occurred", e);
                }
            }
        });

        log.info("main begin ...");
        thread.start();
        log.info("stop : {} ", stop);
        try {
            thread.join();
            log.info("main end stop : {} ", stop);
        } catch (Exception e) {
            log.error("Exception occurred", e);
        }
    }
}
