package org.liuxinyi.demos.j8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.*;
import java.util.concurrent.FutureTask;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Eric.Liu on 2016/12/5.
 * <p>
 * Lambda 语法 :
 * (parameters) -> expression           表达式
 * (parameters) -> {statements;}        语句
 * </p>
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
        list.add(new Person(20, "Kobe", "USA"));
        list.add(new Person(15, "Jack", "UK"));
        list.add(new Person(25, "Lucy", "USA"));
        list.add(new Person(40, "Eric", "UK"));
        list.add(new Person(30, "Alice", "UK"));
        return list;
    }

    @Test
    public void testSort1() {
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
    public void testSort2() {
        List<String> list = getList();
        log.info("before sort : {}", list);
        Collections.sort(list, (String a, String b) -> {
            return b.compareTo(a);
        });
        log.info("after sort : {}", list);
    }


    @Test
    public void testSort3() {
        List<String> list = getList();
        log.info("before sort : {}", list);
        list.parallelStream().sorted().forEach(s -> {
            log.info(s);
        });
        list.parallelStream().sorted().forEachOrdered(s -> {
            log.info(s);
        });
        log.info("after sort : {}", list);
    }

    /**
     * 必须是类的方法,该方法不能传递参数,不能再方法的基础上做运算
     */
    @Test
    public void testComparing() {
        List<Person> list = getPersonList();
        log.info("before sort : {}", list);
        list.sort(Comparator.comparing(Person::getAge));
        log.info("after sort : {}", list);
    }

    /**
     * 只有5种方法可用,复杂的filter还是要自己写
     */
    @Test
    public void testFileFilter() {
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        File[] files = new File(".").listFiles(File::isFile);
        File[] directories = new File(".").listFiles(File::isDirectory);
        File[] absoluteFiles = new File(".").listFiles(File::isAbsolute);
        log.info("files size : {} ; hiddenFiles size : {} ; directories size : {} ; absoluteFiles size : {} ;"
                , files.length, hiddenFiles.length, directories.length, absoluteFiles.length);
    }


    @Test
    public void testStreamFilter() {
        Predicate<String> predicate = str -> str.length() < 7 && !str.equals("java");
        List<String> list = getList();
        long count = list.stream().filter(predicate).count();
        long c = list.stream().filter((String str) -> str.length() < 7 && !str.equals("java")).count();

        log.info("count : {} ; count2 : {}", count, c);
    }

    @Test
    public void testStreamMapTo() {
        List<Person> persons = getPersonList();

        int sum = persons.stream().mapToInt(person -> person.getAge()).sum();
        OptionalDouble avg = persons.stream().mapToInt(person -> person.getAge()).average();
        long sum2 = persons.stream().mapToLong(person -> person.getAge()).sum();
        log.info("age int sum is : {} ; avg : {}", sum, avg);
        log.info("age long sum is : {} ", sum2);
    }

    @Test
    public void testStreamCollect() {
        Map<String, List<Person>> personsByCountry = getPersonList().stream()
                .filter((person -> person.getAge() > 20))
                .collect(Collectors.groupingBy(Person::getCountry));
        Map<String, Long> countByCountry = getPersonList().stream()
                .filter((person -> person.getAge() > 20))
                .collect(Collectors.groupingBy(Person::getCountry,
                        Collectors.counting()));
        Map<String, Long> ageSumByCountry = getPersonList().stream()
                .filter((person -> person.getAge() > 10))
                .collect(Collectors.groupingBy(Person::getCountry,
                        Collectors.summingLong(Person::getAge)));
        Map<String, Double> ageAvgByCountry = getPersonList().stream()
                .filter((person -> person.getAge() > 10))
                .collect(Collectors.groupingBy(Person::getCountry,
                        Collectors.averagingDouble(Person::getAge)));
        log.info("personsByCountry : {} ", personsByCountry);
        log.info("countByCountry : {} ", countByCountry);
        log.info("ageSumByCountry : {} ", ageSumByCountry);
        log.info("ageAvgByCountry : {} ", ageAvgByCountry);
    }

    @Test
    public void testRunnable() {
        Thread t = new Thread(() -> {
            List<Person> list = getPersonList();
            list.stream().forEach(person -> {
                log.info("person : {} ", person);
            });
        });
        t.start();
        try {
            t.join();
        } catch (Exception e) {
            log.error("error ", e);
        }
    }

    @Test
    public void testCallable() {
        FutureTask sizeTask = new FutureTask(() -> {
            List<Person> list = getPersonList();
            return list.size();
        });
        Thread t = new Thread(sizeTask);
        t.start();
        try {
            t.join();
            log.info("size : {} ", sizeTask.get());
        } catch (Exception e) {
            log.error("error ", e);
        }
    }
}
