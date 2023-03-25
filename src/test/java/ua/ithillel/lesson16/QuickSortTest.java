package ua.ithillel.lesson16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {


    @Test
    public void shouldSorting() {
        int[] array = {23, 1, 15, 10, 4, 18, 3, 11, 20, 12, 7};
        int[] arraySorted = {1, 3, 4, 7, 10, 11, 12, 15, 18, 20, 23};

        QuickSort.sorting(array, 0, array.length-1);

        assertEquals(11, array.length);
        assertArrayEquals(arraySorted, array);
    }
}