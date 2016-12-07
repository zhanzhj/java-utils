package org.liuxinyi.demos.j8;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
                sum, sum1, sum2.orElse(-1), max);
    }


    /**
     * 求笛卡尔对数
     */
    @Test
    public void testR() {
        List<Integer> list1 = Arrays.asList(3, 4, 5);
        List<Integer> list2 = Arrays.asList(2, 4);
        List<int[]> pairs = list1.stream()
                .flatMap(i -> list2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        log.info("pairs : {} ", JSON.toJSONString(pairs));
    }

    /**
     * 求笛卡尔对数中和能被3整除的
     */
    @Test
    public void testR1() {
        List<Integer> list1 = Arrays.asList(3, 4, 5);
        List<Integer> list2 = Arrays.asList(2, 4);
        List<int[]> pairs = list1.stream()
                .flatMap(i -> list2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        log.info("pairs : {} ", JSON.toJSONString(pairs));
    }


    /**
     * 求笛卡尔对数中和能被3整除的
     */
    @Test
    public void testSqrt() {
        log.info("pairs : {} ", Math.sqrt(5) % 1 == 0);
        log.info("pairs : {} ", Math.sqrt(6) % 1 == 0);
        log.info("pairs : {} ", Math.sqrt(9) % 1 == 0);
        log.info("pairs : {} ", Math.sqrt(4) % 1 == 0);
    }


    /**
     * 求勾股数
     */
    @Test
    public void testPythagoreanTriples() {
        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(1, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b ->
                                        new int[]{a, b, (int) Math.sqrt(a * a + b * b)}
                                )
                ).limit(6)
                .forEach(t ->
                        log.info(JSON.toJSONString(t))
                );
    }

    @Test
    public void testFilesLines() {
        long uniqueWords = 0;
        try (Stream<String> lines =
                     Files.lines(Paths.get("C:/tmp/test.txt"))) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            log.info("uniqueWords : {} ", uniqueWords);
        } catch (IOException e) {
            log.error("IOException ", e);
        }
    }

    @Test
    public void testStream() {
        // 斐波那契数列
        List<Integer> list = Stream.iterate(new int[]{0, 1}, t ->
                new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .collect(Collectors.toList());
        log.info("list : {} ", list);

        // generate
        List<Double> doubles = Stream.generate(Math::random)
                .limit(4)
                .collect(Collectors.toList());
        log.info("doubles : {} ", doubles);
    }

    @Test
    public void testIntSupplier() {
        // 斐波那契数列
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = previous;
                current = previous + current;
                previous = current - oldPrevious;
                return oldPrevious;
            }
        };

        IntStream.generate(fib).limit(10)
                .skip(5)
                .forEach(System.out::println);
    }


}
