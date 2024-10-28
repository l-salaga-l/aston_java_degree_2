package org.example.homework.hw1;

import java.util.*;

public class CustomArrayListImpl<E> extends AbstractCustomArrayList<E>
        implements CustomArrayList<E>{

    /**
     * Стандартная емкость по умолчанию для списка
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Пустой массив для обозначения состояния
     */
    private static final Object[] EMPTY_ARRAY = {};

    /**
     * Массив для хранения элементов списка
     */
    Object[] elements;

    /**
     * Текущее количество элементов в списке
     */
    private int size;

    /**
     * Конструктор по умолчанию, инициализирует пустой список
     */
    public CustomArrayListImpl() {
        elements = EMPTY_ARRAY;
        size = 0;
    }

    /**
     * Конструктор с указанием емкости списка
     * @param capacity начальная емкость списка
     * @throws IllegalArgumentException если емкость < 0
     */
    public CustomArrayListImpl(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer");
        } else if (capacity == 0) {
            elements = EMPTY_ARRAY;
        } else {
            elements = new Object[capacity];
        }
    }

    /**
     * Конструктор, инициализирующий список элементами из указанной коллекции
     * @param c коллекция, элементы которой будут добавлены в список
     */
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

    /**
     * Возвращает текущее количество элементов в списке
     * @return размер списка
     */
    public int size() {
        return size;
    }

    /**
     * Добавляет элемент в конец списка
     * @param element элемент, который нужно добавить
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * Вставляет указанный элемент по заданному индексу
     *
     * @param index индекс, по которому нужно вставить элемент
     * @param element элемент, который нужно вставить
     * @throws IndexOutOfBoundsException если индекс вне допустимого диапазона
     */
    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        resize();

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Добавляет все элементы из указанной коллекции в конец списка
     *
     * @param c коллекция, элементы которой будут добавлены в список
     */
    @Override
    public void addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(size, e);
        }
    }

    /**
     * Очищает список, удаляя все элементы
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Возвращает элемент по указанному индексу
     *
     * @param index индекс элемента, который нужно получить
     * @return элемент, находящийся по заданному индексу
     * @throws IndexOutOfBoundsException если индекс вне допустимого диапазона
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    /**
     * Проверяет, пуст ли список
     * @return true, если список пуст, иначе false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *  Удаляет элемент из списка по переданному индексу
     * @param index индекс элемента, который необходимо удалить
     * @return значение элемента, который удалили
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        E result = (E) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return result;
    }

    /**
     * Удаляет первое вхождение указанного элемента из списка
     *
     * @param o элемент, который нужно удалить
     * @return true, если элемент был удален, иначе false
     */
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

    /**
     * Сортирует элементы списка в соответствии с заданным компаратором
     * @param c компаратор, определяющий порядок сортировки
     */
    @Override
    public void sort(Comparator<? super E> c) {
        if (size > 1) {
            quicksort(c);
        }
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

    /**
     * Изменяет размер массива, если он заполнен
     */
    private void resize() {
        if (size == elements.length) {
            elements = grow();
        }
    }

    /**
     * Увеличивает размер массива при необходимости
     * @return новый массив с увеличенной емкостью
     */
    private Object[] grow() {
        return grow(size + 1);
    }

    /**
     * Увеличивает размер массива до указанной емкости
     * @param size новая требуемая емкость
     * @return новый массив с увеличенной емкостью
     */
    private Object[] grow(int size) {
        int oldSize = elements.length;
        if (oldSize > 0 || elements != EMPTY_ARRAY) {
            int newSize = oldSize * 2 + 1;
            return elements = Arrays.copyOf(elements, newSize);
        } else {
            return elements = new Object[Math.max(DEFAULT_CAPACITY, size)];
        }
    }

    /**
     * Проверяет, находится ли индекс в допустимом диапазоне
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс вне диапазона
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Проверяет, может ли быть добавлен элемент по указанному индексу
     * @param index индекс для проверки на добавление
     * @throws IndexOutOfBoundsException если индекс вне допустимого диапазона
     */
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Реализация алгоритма быстрой сортировки
     * @param c компаратор, определяющий порядок сортировки
     */
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

            if (left < indexPivot) {
                indexStack[++head] = left;
                indexStack[++head] = indexPivot;
            }

            if (indexPivot + 1 < right) {
                indexStack[++head] = indexPivot + 1;
                indexStack[++head] = right;
            }
        }
    }

    /**
     * Разделяет подмассив на две части, используя метод разбиения для быстрой сортировки.
     * Выбирает опорный элемент и перемещает элементы таким образом,
     * чтобы элементы, меньшие опорного, находились слева, а большие - справа.
     *
     * @param array массив элементов, который нужно разобрать
     * @param left левая граница подмассива
     * @param right правая граница подмассива
     * @param c компаратор, определяющий порядок сортировки
     * @return индекс, по которому подмассив разделен
     */
    private int partition(Object[] array, int left, int right, Comparator<? super E> c) {
        E pivot = (E) array[left];
        int i = left - 1, j = right + 1;

        while (true) {
            do {
                i++;
            } while (c.compare((E) array[i], pivot) < 0);

            do {
                j--;
            } while (c.compare((E) array[j], pivot) > 0);

            if (i >= j) {
                return j;
            }

            E temp = (E) array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
