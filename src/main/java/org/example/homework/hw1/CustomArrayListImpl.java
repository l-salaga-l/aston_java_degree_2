package org.example.homework.hw1;

import java.util.*;

public class CustomArrayListImpl<E> extends AbstractCustomArrayList<E>
        implements CustomArrayList<E>{

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ARRAY = {};

    Object[] elements;

    private int size;

    public CustomArrayListImpl() {
        elements = EMPTY_ARRAY;
        size = 0;
    }

    public CustomArrayListImpl(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer");
        } else if (capacity == 0) {
            elements = EMPTY_ARRAY;
        } else {
            elements = new Object[capacity];
        }
    }

    public CustomArrayListImpl(Collection<? extends E> c) {
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

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        resize();

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public void addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(size, e);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E result = (E) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return result;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void sort(Comparator<? super E> c) {
        quicksort(c);
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i <= size; i++) {
            E e = (E) elements[i];
            sb.append(e);
            if (i == size - 1) {
                return sb.append(']').toString();
            }
            sb.append(',').append(' ');
        }
        return sb.toString();
    }

    private void resize() {
        if (size == elements.length) {
            elements = grow();
        }
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int size) {
        int oldSize = elements.length;
        if (oldSize > 0 || elements != EMPTY_ARRAY) {
            int newSize = oldSize * 2 + 1;
            return elements = Arrays.copyOf(elements, newSize);
        } else {
            return elements = new Object[Math.max(DEFAULT_CAPACITY, size)];
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
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
        while (left < right) {
            while (c.compare((E) array[right], pivot) > 0) {
                right--;
            }
            while (c.compare(pivot, (E) array[left]) > 0) {
                left++;
            }
            if (left < right) {
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
