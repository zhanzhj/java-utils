package org.liuxinyi.demos.multiThread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * Created by Eric.Liu on 2016/11/21.
 */
@Slf4j
public class LockThread implements Runnable {
    private LockBean lockBean;

    public LockThread(LockBean lockBean) {
        this.lockBean = lockBean;
    }

    @Override
    public void run() {
        lockBean.getWriteLock();
        lockBean.setNum(lockBean.getNum() + 1);
        log.info("num : {} ", lockBean.getNum());
        try {
            Thread.sleep(new Random().nextInt(4) * 1000);
        } catch (Exception e) {
            log.error("Exception", e);
        } finally {
            lockBean.releaseWriteLock();
        }
    }
}
