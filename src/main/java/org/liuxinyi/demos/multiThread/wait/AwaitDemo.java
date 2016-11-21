package org.liuxinyi.demos.multiThread.wait;

import org.junit.Test;

/**
 * Created by Eric.Liu on 2016/11/21.
 */
public class AwaitDemo {

    @Test
    public void test() {
        Object object = new Object();
        Thread thread = new Thread(new AwaitThread(object));
        try {
            thread.start();
        } catch (Exception e) {

        }
    }
}
