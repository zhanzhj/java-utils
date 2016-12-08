package org.liuxinyi.demos.j8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by Eric.Liu on 2016/12/8.
 */
@Slf4j
public class ParallelDemo extends CommonData {
    @Test
    public void testParallelSum() {
        log.info("sum is : {} ", parallelSum(80_000_000));
    }

    @Test
    public void testLongStreamParallelSum() {
        log.info("sum is : {} ", longStreamParallelSum(80_000_000));
    }

    private long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    private long longStreamParallelSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }
}
