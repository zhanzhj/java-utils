package org.liuxinyi.demos.multiThread.cyclic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by Eric.Liu on 2016/11/22.
 */
@Slf4j
public class CyclicDemo implements Runnable {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    private int seconds;

    public CyclicDemo(int seconds) {
        this.seconds = seconds;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new CyclicDemo(i + 1));
            thread.start();
        }
    }

    @Override
    public void run() {
        try {
            // cyclicBarrier.await(); // 等待所有人到齐
            playBadminton();
            cyclicBarrier.await(); // 等待所有人打完羽毛球比赛准备开始篮球比赛
            playBasketball();
            cyclicBarrier.await(); // 活动结束等待所有人集合
            goHome();
            eat();
        } catch (Exception e) {
            log.error("e", e);
        }
    }

    private void playBadminton() {
        try {
            log.info("thread : {} sing begin", Thread.currentThread().getName());
            Thread.sleep(seconds * 1000);
            log.info("thread : {} sing ends", Thread.currentThread().getName());
        } catch (Exception e) {
            log.error("e", e);
        }
    }

    private void playBasketball() {
        try {
            log.info("thread : {} dance begin", Thread.currentThread().getName());
            Thread.sleep(seconds * 1000);
            log.info("thread : {} dance ends", Thread.currentThread().getName());
        } catch (Exception e) {
            log.error("e", e);
        }
    }

    private void goHome() {
        try {

            log.info("thread : {} goHome begin", Thread.currentThread().getName());
            Thread.sleep(seconds * 1000);
            log.info("thread : {} goHome ends", Thread.currentThread().getName());
        } catch (Exception e) {
            log.error("e", e);
        }
    }

    private void eat() {
        try {
            log.info("thread : {} eat begin", Thread.currentThread().getName());
            Thread.sleep(1 * 1000);
            log.info("thread : {} eat ends", Thread.currentThread().getName());
        } catch (Exception e) {
            log.error("e", e);
        }
    }
}
