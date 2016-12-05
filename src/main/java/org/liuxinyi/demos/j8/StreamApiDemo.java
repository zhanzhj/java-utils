package org.liuxinyi.demos.j8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by Eric.Liu on 2016/12/5.
 */
@Slf4j
public class StreamApiDemo {

    private List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("z");
        list.add("sql");
        list.add("java");
        list.add("perl");
        list.add("python");
        list.add("language");
        return list;
    }

    private List<Person> getPersonList() {
        List<Person> list = new ArrayList<>();
        list.add(new Person(15, "Jack"));
        list.add(new Person(20, "Kobe"));
        list.add(new Person(25, "Lucy"));
        list.add(new Person(30, "Alice"));
        list.add(new Person(40, "Eric"));
        return list;
    }

    @Test
    public void test1() {
        List<String> list = getList();
        log.info("before sort : {}", list);
        list.parallelStream().sorted().count();
        String[] array = new String[list.size()];
        Arrays.parallelSort(list.toArray(array));
        Arrays.stream(array).forEach(str -> {
            log.info(str);
        });
    }

    @Test
    public void test2() {
        Predicate<String> predicate = str -> str.length() < 7 && !str.equals("java");
        List<String> list = getList();
        long count = list.stream().filter(predicate).count();

        log.info("count : {} ", count);
    }

    @Test
    public void test3() {
        List<String> list = getList();
        list.stream().forEach(str -> {
            log.info(str);
        });
    }

    @Test
    public void test4() {
        List<Person> persons = getPersonList();

        int sum = persons.stream().mapToInt(person -> person.getAge()).sum();
        OptionalDouble avg = persons.stream().mapToInt(person -> person.getAge()).average();
        long sum2 = persons.stream().mapToLong(person -> person.getAge()).sum();
        log.info("age int sum is : {} ; avg : {}", sum, avg);
        log.info("age long sum is : {} ", sum2);
    }
}
