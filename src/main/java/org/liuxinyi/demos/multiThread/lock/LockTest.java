package org.liuxinyi.demos.multiThread.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Eric.Liu on 2016/11/21.
 */
@Slf4j
public class LockTest {
    private Lock lock1 = new ReentrantLock();
    private ReentrantReadWriteLock lock2 = new ReentrantReadWriteLock();

    private static int num = 0;

    /**
     * test common lock
     */
    @Test
    public void test1() {

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock1.tryLock(2000, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        log.error("failed to get lock within 2 seconds");
                        return;
                    }
                    log.info(Thread.currentThread().getName() + "lock");
                    try {
                        Thread.sleep(new Random().nextInt(6) * 1000);
                    } catch (Exception e) {
                        log.error("Exception", e);
                    }
                    lock1.unlock();
                    log.info(Thread.currentThread().getName() + "unlock");
                }
            });
            thread.setName("thread" + i);
            thread.start();
        }
        try {
            Thread.sleep(20000);
        } catch (Exception e) {
            log.error("Exception", e);
        }


    }

    /**
     * test read write lock
     */
    @Test
    public void test2() {
        LockBean lockBean = new LockBean(30);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new LockThread(lockBean));
            thread.start();
        }
        try {
            Thread.sleep(12 * 1000);
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }

}
