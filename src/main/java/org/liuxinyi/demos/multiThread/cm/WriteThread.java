package org.liuxinyi.demos.multiThread.cm;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Eric.Liu on 2016/11/23.
 */
@Slf4j
public class WriteThread implements Runnable {
    private ReenLockBean lockBean;

    public WriteThread(ReenLockBean lockBean) {
        this.lockBean = lockBean;
    }

    @Override
    public void run() {
        lockBean.lock();
        log.info("lock");
        lockBean.setAge(20);
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            log.error("err", e);
        } finally {
            lockBean.unlock();
            log.info("unlock");
        }
    }
}
