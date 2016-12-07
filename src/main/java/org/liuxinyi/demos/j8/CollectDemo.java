package org.liuxinyi.demos.j8;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by Eric.Liu on 2016/12/7.
 */
@Slf4j
public class CollectDemo extends CommonData {

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
    public void testMaxBy() {
        Map<String, Optional<Person>> countryMaxAgePersons = getPersonList().stream()
                .collect(Collectors.groupingBy(Person::getCountry,
                        Collectors.maxBy(Comparator.comparingInt(Person::getAge))));
        log.info("countryMaxAgePersons : {} ", countryMaxAgePersons);

        Map<String, Person> countryMaxAgePersons2 = getPersonList().stream()
                .collect(Collectors.groupingBy(Person::getCountry,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Person::getAge)),
                                Optional::get)));
        log.info("countryMaxAgePersons2 : {} ", countryMaxAgePersons2);
    }

    @Test
    public void testMapping() {
        Map<String, Set<Integer>> countryAgeLevel = getPersonList().stream()
                .collect(Collectors.groupingBy(Person::getCountry,
                        Collectors.mapping(
                                person -> {
                                    if (person.getAge() < 25) {
                                        return 1;
                                    } else if (person.getAge() < 40) {
                                        return 2;
                                    } else {
                                        return 3;
                                    }

                                },
                                Collectors.toSet()
                        )));
        log.info("countryAgeLevel : {} ", countryAgeLevel);

        Map<String, Set<Integer>> countryAgeLevel2 = getPersonList().stream()
                .collect(Collectors.groupingBy(Person::getCountry,
                        Collectors.mapping(
                                person -> {
                                    if (person.getAge() < 26) {
                                        return 1;
                                    } else if (person.getAge() < 40) {
                                        return 2;
                                    } else {
                                        return 3;
                                    }

                                },
                                Collectors.toCollection(HashSet::new)
                        )));
        log.info("countryAgeLevel2 : {} ", countryAgeLevel2);
    }


}
