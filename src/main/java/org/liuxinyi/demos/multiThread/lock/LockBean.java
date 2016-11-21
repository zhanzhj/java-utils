package org.liuxinyi.demos.multiThread.lock;

import lombok.Setter;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Eric.Liu on 2016/11/21.
 */

@Setter
public class LockBean {
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private int num;

    public int getNum() {
        return num;
    }

    public LockBean(int num) {
        this.num = num;
    }

    public void getReadLock() {
        readWriteLock.readLock().lock();
    }

    public void releaseReadLock() {
        readWriteLock.readLock().unlock();
    }

    public void getWriteLock() {
        readWriteLock.writeLock().lock();
    }

    public void releaseWriteLock() {
        readWriteLock.writeLock().unlock();
    }

}
