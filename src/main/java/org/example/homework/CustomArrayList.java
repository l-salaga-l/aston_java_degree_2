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
        checkIndex(index);
        resize();

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    public void addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(size, e);
        }
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E remove(int index) {
        checkIndex(index);
        E result = (E) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return result;
    }

    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public void sort(Comparator<? super E> c) {
        quicksort(c);
    }

    private void resize() {
        if (size == elements.length) {
            size = elements.length * 2 + 1;
            elements = Arrays.copyOf(elements, size);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void quicksort(Comparator<? super E> c) {
        int left = 0, right = size - 1;

        // Хранилище начальных и конечных индексов подмассивов
        int[] indexStack = new int[size];
        int head = -1;

        indexStack[++head] = left;
        indexStack[++head] = right;

        while (head >= 0) {
            // Извлекаем левую и правую границу
            right = indexStack[head--];
            left = indexStack[head--];

            // Выполняем разбиение
            int indexPivot = partition(elements, left, right, c);

            if (left < indexPivot - 1) {
                indexStack[++head] = left;
                indexStack[++head] = indexPivot;
            }

            if (indexPivot + 1 < right) {
                indexStack[++head] = indexPivot + 1;
                indexStack[++head] = right;
            }
        }
    }

    private int partition(Object[] array, int left, int right, Comparator<? super E> c) {
        E pivot = (E) array[(left + right) / 2];
        while (left <= right) {
            while (c.compare((E) array[right], pivot) < 0) {
                right--;
            }
            while (c.compare((E) array[left], pivot) > 0) {
                left++;
            }
            if (left <= right) {
                E temp = (E) array[right];
                array[right] = array[left];
                array[left] = temp;
                left++;
                right--;
            }
        }
        return left;
    }
}
