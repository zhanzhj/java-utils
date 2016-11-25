package org.liuxinyi.demos.multiThread.fc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by Eric.Liu on 2016/11/22.
 */
@Slf4j
public class CallableTest {
    @Test
    public void test() {
        List<Integer> numbers = new ArrayList<>(5);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new SumTask(numbers));
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            thread.join();
            // futureTask.isDone();
            log.info("sum is : {} ", futureTask.get());
        } catch (Exception e) {
            log.error("", e);
        }

    }

    @Test
    public void test1() {
        List<Integer> numbers = new ArrayList<>(5);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new SumTask(numbers));
        executorService.shutdown();
        try {
            log.info("sum is : {} ", future.get());
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
