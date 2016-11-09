package org.liuxinyi.demos.multiThread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by Eric.Liu on 2016/11/9.
 */
@Slf4j
public class JoinDemo {
    @Test
    public void test() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("thread begin ...");
                    Thread.sleep(5000);
                    log.info("thread end ...");
                } catch (Exception e) {
                    log.error("Exception occurred", e);
                }
            }
        });

        log.info("main begin ...");
        thread.start();
        log.info("main run ...");
        try {
            thread.join();
            log.info("main end ...");
        } catch (Exception e) {
            log.error("Exception occurred", e);
        }
    }
}
