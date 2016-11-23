package org.liuxinyi.demos.multiThread.cm;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Eric.Liu on 2016/11/23.
 */
@Slf4j
public class ReadThread implements Runnable {
    private ReenLockBean lockBean;

    public ReadThread(ReenLockBean lockBean) {
        this.lockBean = lockBean;
    }

    @Override
    public void run() {
        log.info(lockBean.getAge() + "");
    }
}
