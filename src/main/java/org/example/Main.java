package org.example;

import org.example.homework.hw1.CustomArrayListImpl;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        CustomArrayListImpl<Integer> list = new CustomArrayListImpl<>();
        list.add(0, 5);
        list.add(1, 2);
        list.add(2, 4);
        list.add(3, 1);
        list.add(4, 3);

        Collection<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);

        list.addAll(list2);

        System.out.println(list);
        list.sort(Integer::compareTo);
        System.out.println(list);
    }
}