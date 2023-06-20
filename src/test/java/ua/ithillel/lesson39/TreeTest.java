package ua.ithillel.lesson39;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
class TreeTest {

    @Test
    void shouldInsert() {

        Tree tree = new Tree();

        // Вставка чисел
        tree.insert(8);
        tree.insert(3);
        tree.insert(10);
        tree.insert(1);
        tree.insert(6);
        tree.insert(4);
        tree.insert(7);
        tree.insert(14);
        tree.insert(13);

        List<Integer> arrayExpected = Arrays.asList(1, 3, 4, 6, 7, 8, 10, 13, 14);
        assertEquals(arrayExpected, tree.traverse());
    }
}