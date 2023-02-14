package ua.ithillel.lesson6;

import java.util.ArrayList;
import java.util.List;

public class MyUtils {

    public int countOccurrence(List<String> list, String item){
        int counter = 0;
        for (String elem : list) {
            if(elem.equals(item)){
                ++counter;
            }
        }

        return counter;
    }

    public List<Integer> toList(int[] array){
        List<Integer> list = new ArrayList<>();

        for (int elem : array) {
            list.add(elem);
        }
        return list;
    }

    // --- берем в верхнем цикле 1-й объект списка, далее во вложенном цикле пробежимся по всему списку и при совпадении
    // --- объекта удаляем его из текущего списка
    public List findUnique(List<Integer> list){

        for (int i = 0; i < list.size(); i++) {
            Integer iElem = list.get(i);

            for (int j = i+1; j < list.size(); j++) {
                if(iElem.equals(list.get(j))){
                    list.remove(j);
                }
            }
        }
        System.out.println(list);
        return list;
    }


}
