package ua.ithillel.lesson6;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyUtilsTest {

    @Test
    void shouldCountOccurrence() {
        List<String> list = new ArrayList<String>();
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
    void shouldToArray(){
        int[] array = {10, 5, 2, 15};

        List list = new ArrayList<Integer>();
        list.add(10);
        list.add(5);
        list.add(2);
        list.add(15);

        MyUtils myUtils = new MyUtils();
        assertTrue(list.containsAll(myUtils.toList(array)));
    }

    @Test
    void shouldFindUnique(){
        List list = new ArrayList<Integer>();
        list.add(15);
        list.add(5);
        list.add(2);
        list.add(15);
        list.add(2);
        list.add(22);
        list.add(38);
        list.add(2);

        Set set = new HashSet<Integer>();
        set.add(list);

        MyUtils myUtils = new MyUtils();
        assertTrue(set.containsAll(myUtils.findUnique(list)));
    }
}