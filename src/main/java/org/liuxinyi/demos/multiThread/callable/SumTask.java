package org.liuxinyi.demos.multiThread.callable;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Eric.Liu on 2016/11/21.
 */
@Slf4j
public class SumTask implements Callable<Integer> {

    private List<Integer> numbers;

    public SumTask(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (Integer integer : numbers) {
            sum += integer;
        }
        return sum;
    }


}
