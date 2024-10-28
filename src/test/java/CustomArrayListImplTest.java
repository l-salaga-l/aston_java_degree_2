import org.example.homework.hw1.CustomArrayListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListImplTest {
    private CustomArrayListImpl<Integer> customArrayList;

    @BeforeEach
    void setUp() {
        customArrayList = new CustomArrayListImpl<>();
    }

    @Test
    void testDefaultConstructor() {
        assertTrue(customArrayList.isEmpty());
    }

    @Test
    void testCapacityConstructorPositive() {
        CustomArrayListImpl<String> listWithCapacity = new CustomArrayListImpl<>(5);
        assertNotNull(listWithCapacity);
        assertTrue(listWithCapacity.isEmpty());
    }

    @Test
    void testCapacityConstructorNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CustomArrayListImpl<>(-1);
        });
        assertEquals("Capacity must be a positive integer", exception.getMessage());
    }

    @Test
    void testClear() {
        customArrayList.add(0, 1);
        customArrayList.add(1,2);
        customArrayList.clear();
        assertTrue(customArrayList.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> customArrayList.get(0));
    }

    @Test
    void testGetIndexOutOfBounds() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            customArrayList.get(0);
        });
        assertEquals("Index: 0, Size: 0", exception.getMessage());
    }

    @Test
    void testRemoveByIndex() {
        CustomArrayListImpl<String> list = new CustomArrayListImpl<>();
        list.add(0, "A");
        list.add(1,"B");
        list.add(2,"C");
        String removed = list.remove(1);
        assertEquals("B", removed);
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }

    @Test
    void testRemoveByObject() {
        CustomArrayListImpl<String> list = new CustomArrayListImpl<>();
        list.add(0,"A");
        list.add(1,"B");
        assertTrue(list.remove("A"));
        assertEquals(1, list.size());
        assertEquals("B", list.get(0));
        assertFalse(list.remove("A"));
    }

    @Test
    void testRemoveIndexOutOfBounds() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            customArrayList.remove(0);
        });
        assertEquals("Index: 0, Size: 0", exception.getMessage());
    }

    @Test
    void testRemoveObjectNotFound() {
        CustomArrayListImpl<String> list = new CustomArrayListImpl<>();
        list.add(0,"A");
        assertFalse(list.remove("X"));
    }

    @Test
    void testSortEmptyList() {
        customArrayList.sort(Comparator.naturalOrder());
        assertEquals("[]", customArrayList.toString());
    }

    @Test
    void testSortSingleElement() {
        customArrayList.add(0, 5);
        customArrayList.sort(Comparator.naturalOrder());
        assertEquals("[5]", customArrayList.toString());
    }

    @Test
    void testSortAlreadySorted() {
        customArrayList.add(0, 1);
        customArrayList.add(1, 2);
        customArrayList.add(2, 3);
        customArrayList.sort(Comparator.naturalOrder());
        assertEquals("[1, 2, 3]", customArrayList.toString());
    }

    @Test
    void testSortReverseOrder() {
        customArrayList.add(0, 3);
        customArrayList.add(1, 2);
        customArrayList.add(2, 1);
        customArrayList.sort(Comparator.naturalOrder());
        assertEquals("[1, 2, 3]", customArrayList.toString());
    }

    @Test
    void testSortRandomOrder() {
        customArrayList.add(0, 5);
        customArrayList.add(1, 3);
        customArrayList.add(2, 8);
        customArrayList.add(3, 1);
        customArrayList.add(4, 7);
        customArrayList.sort(Comparator.naturalOrder());
        assertEquals("[1, 3, 5, 7, 8]", customArrayList.toString());
    }

    @Test
    void testSortStrings() {
        CustomArrayListImpl<String> stringList = new CustomArrayListImpl<>();
        stringList.add(0, "banana");
        stringList.add(1, "apple");
        stringList.add(2, "orange");
        stringList.sort(Comparator.naturalOrder());
        assertEquals("[apple, banana, orange]", stringList.toString());
    }

    @Test
    void testSortWithCustomComparator() {
        customArrayList.add(0, 1);
        customArrayList.add(1, 2);
        customArrayList.add(2, 3);
        customArrayList.add(3, 2);
        customArrayList.sort(Comparator.reverseOrder());
        assertEquals("[3, 2, 2, 1]", customArrayList.toString());
    }
}  