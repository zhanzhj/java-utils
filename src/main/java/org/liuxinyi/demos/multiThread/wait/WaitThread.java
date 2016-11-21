package org.liuxinyi.demos.multiThread.wait;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Eric.Liu on 2016/11/21.
 */
@Slf4j
public class WaitThread implements Runnable {

    private Object object;

    public WaitThread(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        try {
            synchronized (object) {
                log.info("before wait");
                object.wait();
                log.info("after wait");
            }
        } catch (InterruptedException e) {
            log.error("InterruptedException ", e);
        }
    }
}
