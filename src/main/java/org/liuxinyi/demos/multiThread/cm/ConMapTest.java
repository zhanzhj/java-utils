package org.liuxinyi.demos.multiThread.cm;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Eric.Liu on 2016/11/23.
 */
@Slf4j
public class ConMapTest {

    public static void main(String[] args) {
        ReenLockBean lockBean = new ReenLockBean(10);
        Thread thread = new Thread(new WriteThread(lockBean));
        thread.start();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            log.error("sleep", e);
        }
        Thread thread1 = new Thread(new ReadThread(lockBean));
        thread1.start();
    }
}
