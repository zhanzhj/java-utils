package org.liuxinyi.demos.j8;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Eric.Liu on 2016/12/5.
 */
@Getter
@Setter
@ToString
public class Person {
    private int age;
    private String name;
    private String country;

    public Person(int age, String name, String country) {
        this.age = age;
        this.name = name;
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
