package org.liuxinyi.demos.multiThread.wait;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * wait是Object的方法 wait会释放锁
 * 一般不要把wait和join一起使用
 * Created by Eric.Liu on 2016/11/21.
 */
@Slf4j
public class WaitDemo {

    @Test
    public void test() {
        Object object = new Object();
        Thread thread = new Thread(new WaitThread(object));
        try {
            thread.start();
            Thread.sleep(1000); // 保证wait在前
            // thread.join(); // join 产生死锁等待的原因分析 , join需要线程执行完, wait的时候 ,必须要notify
            synchronized (object) {
                log.info("before notify");
                object.notify();
                log.info("after notify");
                Thread.sleep(5000); // 感受synchronized 产生的等待
            }

        } catch (Exception e) {
            log.error("exception ", e);
        }
    }
}
