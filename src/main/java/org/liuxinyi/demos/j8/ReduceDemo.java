package org.liuxinyi.demos.j8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Eric.Liu on 2016/12/6.
 */
@Slf4j
public class ReduceDemo {

    private List<Integer> getNumbers() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(4);
        numbers.add(6);
        numbers.add(5);
        numbers.add(3);
        return numbers;
    }

    @Test
    public void testReduce1() {
        // 有初始值reduce
        int sum = getNumbers().stream().reduce(0, (a, b) -> a + b);
        int sum1 = getNumbers().stream().reduce(0, Integer::sum);
        int max = getNumbers().stream().reduce(0, Integer::max);
        // 无初始值reduce
        Optional<Integer> sum2 = getNumbers().stream().reduce((a, b) -> a + b);
        log.info("sum : {} ; sum1 : {} ; sum2 : {} ; max : {} ",
                sum, sum1, sum2.orElse(5), max);
    }
}
