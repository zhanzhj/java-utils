package org.liuxinyi.demos.multiThread.condition;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Eric.Liu on 2016/11/22.
 */
@Slf4j
public class ConditionDemo implements Runnable {

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo conditionDemo = new ConditionDemo();
        Thread thread = new Thread(conditionDemo);
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            log.error("", e);
        }
        log.info("before signal");
        lock.lock();
        condition.signal();
        log.info("after signal");
        lock.unlock();
    }


    @Override
    public void run() {
        try {
            lock.lock();
            log.info("before await");
            condition.await();
            log.info("after await");
        } catch (InterruptedException e) {
            log.error("", e);
        } finally {
            lock.unlock();
        }
    }
}
