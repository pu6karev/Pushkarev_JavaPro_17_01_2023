package ua.ithillel.lesson6;
import java.util.*;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyUtilsTest {

    @Test
    public void shouldCountOccurrence() {
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

        assertEquals(3, MyUtils.countOccurrence(list, "chair"));
    }

    @Test
    public void shouldToList(){
        int[] array = {10, 5, 2, 15};

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(5);
        list.add(2);
        list.add(15);

        assertTrue(list.containsAll(MyUtils.toList(array)));
    }

    @Test
    public void shouldFindUnique(){
        ArrayList<Integer> list = new ArrayList<>();
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

         // --- получим уникальный набор элементов через созданную нами функцию в наборе MyUtil
        List<Integer> myListUnique = MyUtils.findUnique(list);

         // --- сравним двухсторонне, чтобы элементы списка Set и элементы нашей функции полностью совпали
        assertTrue(set.containsAll(myListUnique));
        assertTrue(myListUnique.containsAll(set));
    }

    @Test
    public void shouldCalcOccurrence(){
        ArrayList<String> list = new ArrayList<>();
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

        ArrayList<String> checkList = MyUtils.calcOccurrence(list);
        // --- список вначале состоял из 10 эл-тов, после подсчета совпадений и удаления содержит 7 эл-тов
        assertEquals(7, checkList.size());
    }

    @Test
    public void shouldFindOccurrence(){
        ArrayList<String> list = new ArrayList<>();
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

        ArrayList<Article> artList = MyUtils.findOccurrence(list);
        // --- список вначале состоял из 10 эл-тов, после подсчета совпадений и удаления содержит 7 эл-тов
        assertEquals(7, artList.size());

        // --- узнаем индекс объекта внутри которого есть поле "chair", счетчик должен быть равен 3
        int i;
        for (i = 0; i < artList.size(); i++) {
            if(artList.get(i).getName().equals("chair")){
                break;
            }
        }
        assertEquals(3, artList.get(i).getCounter() );
    }
}