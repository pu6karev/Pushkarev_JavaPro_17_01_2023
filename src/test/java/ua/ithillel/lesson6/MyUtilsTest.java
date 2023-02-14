package ua.ithillel.lesson6;
import java.util.*;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyUtilsTest {

    @Test
    void shouldCountOccurrence() {
        List<String> list = new ArrayList<>();
        list.add("tree");
        list.add("house");
        list.add("bush");
        list.add("button");
        list.add("chair");
        list.add("table");
        list.add("chair");
        list.add("table");
        list.add("spoon");
        list.add("chair");

        MyUtils myUtils = new MyUtils();
        assertEquals(3, myUtils.countOccurrence(list, "chair"));
    }

    @Test
    void shouldToList(){
        int[] array = {10, 5, 2, 15};

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(5);
        list.add(2);
        list.add(15);

        MyUtils myUtils = new MyUtils();
        assertTrue(list.containsAll(myUtils.toList(array)));
    }

    @Test
    void shouldFindUnique(){
        List<Integer> list = new ArrayList<>();
        list.add(15);
        list.add(5);
        list.add(2);
        list.add(15);
        list.add(2);
        list.add(22);
        list.add(38);
        list.add(2);

         // --- создадим список только с уникальным набором значений через интерфейс Set
        Set<Integer> set = new HashSet<>(list);
        //set.addAll(list);

         // --- получим уникальный набор элементов через созданную нами функцию в наборе MyUtil
        MyUtils myUtils = new MyUtils();
        List<Integer> myListUnique = myUtils.findUnique(list);

         // --- сравним двухсторонне, чтобы элементы списка Set и элементы нашей функции полностью совпали
        assertTrue(set.containsAll(myListUnique));
        assertTrue(myListUnique.containsAll(set));
    }
}