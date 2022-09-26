package com.offer;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Test5 {
    public static void main(String[] args) {
//        Map<Person, Integer> map = new TreeMap<>(new Comparator<Person>() {
//            @Override
//            public int compare(Person p1, Person p2) {
//                return p1.name.compareTo(p2.name);
//            }
//        });
        Map<Person, Integer> map = new TreeMap<>((p1,p2)->{
           return  p2.name.compareTo(p1.name);
        });
        map.put(new Person("Tom"), 1);
        map.put(new Person("Bob"), 2);
        map.put(new Person("Lily"), 3);
        for (Person key : map.keySet()) {
            System.out.println(key);
        }
        // {Person: Bob}, {Person: Lily}, {Person: Tom}
        System.out.println(map.get(new Person("Bob"))); // 2



    }
}

class Person {
    public String name;
    Person(String name) {
        this.name = name;
    }
    public String toString() {
        return "{Person: " + name + "}";
    }
}
