package org.liuxinyi.demos.multiThread.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * Created by Eric.Liu on 2016/11/22.
 */
@Slf4j
public class SemaphoreDemo implements Runnable {
    private static final Semaphore semaphore = new Semaphore(5);


    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new SemaphoreDemo());
            thread.start();
        }

    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(3000);
            log.info("thread : {} release", Thread.currentThread().getName());
            semaphore.release();
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
