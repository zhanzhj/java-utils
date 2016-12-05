package org.liuxinyi.demos.j8;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Eric.Liu on 2016/12/5.
 */
@Getter
@Setter
public class Person {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
