package org.liuxinyi.demos.multiThread.cm;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Eric.Liu on 2016/11/23.
 */
public class ReenLockBean extends ReentrantLock{

    private int age;

    public ReenLockBean(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
