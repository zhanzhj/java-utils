package org.liuxinyi.demos.multiThread.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

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
        //testAcquire1();
        testAcquire2();
    }

    private void testAcquire1() {
        try {
            semaphore.acquire();
            Thread.sleep(3000);
            log.info("thread : {} acquired", Thread.currentThread().getName());
        } catch (Exception e) {
            log.error("", e);
        } finally {
            semaphore.release();
            log.info("thread : {} release", Thread.currentThread().getName());
        }
    }


    private void testAcquire2() {
        boolean acquired = false;
        try {
            acquired = semaphore.tryAcquire(1, 500, TimeUnit.MILLISECONDS);
            if (acquired) {
                log.info("thread : {} acquired", Thread.currentThread().getName());
                Thread.sleep(3000);
            } else {
                log.info("thread : {} failed to  acquire", Thread.currentThread().getName());
            }
        } catch (Exception e) {
            log.error("task failed ", e);
        } finally {
            if (acquired) {
                semaphore.release();
                log.info("thread : {} release", Thread.currentThread().getName());
            }
        }
    }
}
