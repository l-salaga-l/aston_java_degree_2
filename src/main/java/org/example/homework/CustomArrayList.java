package org.example.homework;

import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<E> {
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    Object[] elements;

    private int size;

    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public void add(int index, E element) {

    }

    public void addAll(Collection<? extends E> c) {

    }

    public void clear() {

    }

    public E get(int index) {
        return null;
    }

    public boolean isEmpty() {
        return false;
    }

    public E remove(int index) {
        return null;
    }

    public boolean remove(Object o) {
        return false;
    }

    public void sort(Comparator<? super E> c) {

    }
}
