package org.liuxinyi.demos.multiThread.wait;

import lombok.extern.slf4j.Slf4j;

/**
 * simple wait notify demo from 实战Java高并发程序设计 P43-44
 * Created by Eric.Liu on 2016/11/21.
 */
@Slf4j
public class SimpleWN {
    private final static Object OBJ = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (OBJ) {
                try {
                    log.info("before wait");
                    OBJ.wait();
                    log.info("after wait");
                } catch (InterruptedException e) {
                    log.error("InterruptedException ", e);
                }
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (OBJ) {
                log.info("before notify");
                OBJ.notify();
                log.info("after notify");
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    log.error("InterruptedException ", e);
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }
}
