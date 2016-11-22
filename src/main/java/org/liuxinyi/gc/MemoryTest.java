package org.liuxinyi.gc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * Created by Eric.Liu on 2016/9/5.
 */
@Slf4j
public class MemoryTest {

    @Test
    public void test() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        log.info("nonHeapMemoryUsage init : {} ; used : {} ; max : {}",
                nonHeapMemoryUsage.getInit() / 1024 / 1024,
                nonHeapMemoryUsage.getUsed() / 1024 / 1024,
                nonHeapMemoryUsage.getMax() / 1024 / 1024);
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        log.info("heapMemoryUsage init : {} ; used : {} ; max : {}",
                heapMemoryUsage.getInit() / 1024 / 1024,
                heapMemoryUsage.getUsed() / 1024 / 1024,
                heapMemoryUsage.getMax() / 1024 / 1024);
    }
}
