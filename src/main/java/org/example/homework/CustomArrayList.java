package org.example.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class CustomArrayList<E> {
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ARRAY = {};

    Object[] elements;

    private int size;

    public CustomArrayList() {
        elements = EMPTY_ARRAY;
    }

    public CustomArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer");
        } else if (capacity == 0) {
            elements = EMPTY_ARRAY;
        } else {
            elements = new Object[capacity];
        }
    }

    public CustomArrayList(Collection<? extends E> c) {
        Object[] elements = c.toArray();
        if ((size = elements.length) != 0) {
            if (c.getClass() == ArrayList.class) {
                this.elements = elements;
            } else {
                this.elements = Arrays.copyOf(elements, size, Object[].class);
            }
        } else {
            this.elements = EMPTY_ARRAY;
        }
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
