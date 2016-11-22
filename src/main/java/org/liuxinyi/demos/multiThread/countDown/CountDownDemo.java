package org.liuxinyi.demos.multiThread.countDown;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Eric.Liu on 2016/11/22.
 */
@Slf4j
public class CountDownDemo implements Runnable {
    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new CountDownDemo());
            thread.start();
        }
        try {
            countDownLatch.await(); // 等待所有任务完成
            log.info("all task is done!");
        } catch (Exception e) {
            log.error("e", e);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(6) * 1000);
            countDownLatch.countDown();
            log.info("thread : {} is ready! ", Thread.currentThread().getName());
        } catch (Exception e) {
            log.error("e", e);
        }
    }
}
