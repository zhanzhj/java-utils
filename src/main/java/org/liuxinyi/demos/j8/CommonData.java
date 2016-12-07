package org.liuxinyi.demos.j8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric.Liu on 2016/12/7.
 */
public class CommonData {
    List<String> getList() {
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

    List<Person> getPersonList() {
        List<Person> list = new ArrayList<>();
        list.add(new Person(20, "Kobe", "USA"));
        list.add(new Person(15, "Jack", "UK"));
        list.add(new Person(25, "Lucy", "USA"));
        list.add(new Person(40, "Eric", "UK"));
        list.add(new Person(30, "Alice", "UK"));
        return list;
    }
}
