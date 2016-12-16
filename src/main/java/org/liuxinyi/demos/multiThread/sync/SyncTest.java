package org.liuxinyi.demos.multiThread.sync;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
@Slf4j
public class SyncTest {

    @Test
    public void test() throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            doSomething(3);
                            System.out.println("OK");
                        }
                    }
            );
            thread.start();
        }
        try {
            Thread.sleep(30000);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private void doSomething(Integer a) {
        synchronized (a) {
            try {
                System.out.println("in method");
                Thread.sleep(3000);
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

}
