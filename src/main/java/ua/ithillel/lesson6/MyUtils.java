package ua.ithillel.lesson6;

import java.util.ArrayList;
import java.util.List;

public class MyUtils {

    /*
      1. Створити метод countOccurance, що приймає на вхід рядковий список як параметр і довільний рядок. Список
      заповнений довільними словами 10-20 штук, які можуть повторюватись у необмеженій кількості. Порахувати скільки
      разів зустрічається переданий рядок як другий аргумент.
    */
    public int countOccurrence(List<String> list, String item){
        int counter = 0;
        for (String elem : list) {
            if(elem.equals(item)){
                ++counter;
            }
        }

        return counter;
    }

    /*
      2. Створити метод toList, що приймає на вхід цілісний масив довільної довжини. Конвертувати масив у список
      відповідного типу даних та повернути з методу.
    */
    public List<Integer> toList(int[] array){
        List<Integer> list = new ArrayList<>();

        for (int elem : array) {
            list.add(elem);
        }
        return list;
    }

    /*
      3. Створити метод findUnique, що приймає на вхід числовий список, що складається з довільних значень, які можуть
      повторюватися в необмеженій кількості. Необхідно повернути новий числовий список, що містить тільки унікальні числа.
      (Використовуючи лише List)
    */
    // --- берем в верхнем цикле 1-й объект списка, далее во вложенном цикле пробежимся по всему списку и при совпадении
    // --- объекта удаляем его из текущего списка
    public List<Integer> findUnique(List<Integer> list){

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

    /*
      4. ** Створити метод calcOccurance, який приймає на вхід рядковий список як параметр. Список заповнений довільними
      словами 10-20 штук, які можуть повторюватись у необмеженій кількості. Обчислити скільки разів трапляється кожне
      слово. Результат вивести у консоль.
    */
    public void calcOccurrence(ArrayList<String> list){

        for (int i = 0; i < list.size(); i++) {
            String iElem = list.get(i);

            int counter = 1;
            for (int j = i + 1; j < list.size(); j++) {
                if (iElem.equals(list.get(j))) {
                    counter++;
                    list.remove(j);
                }
            }
            System.out.println(iElem + ": " + counter);
        }
    }


    /*
    5. *** Створити метод findOccurance, що приймає на вхід рядковий список як параметр. Список заповнений довільними
    словами 10-20 штук, які можуть повторюватись у необмеженій кількості. Обчислити скільки разів трапляється кожне
    слово. Результат повернути у вигляді списку структур, що описують повторення кожного окремого взятого слова.
     */
    public ArrayList<Article> findOccurrence(ArrayList<String> list){

        ArrayList<Article> uniqueList = new ArrayList<>();      // список будет содержать объекты с уникальными строками

        for (int i = 0; i < list.size(); i++) {
            String iElem = list.get(i);                         // из строкового списка достаем строку
            Article article = new Article(iElem);               // помещаем строку в новый объект со счетчиком = 1

            for (int j = i + 1; j < list.size(); j++) {         // перебор всех остальных строк со след.эл-та
                if (iElem.equals(list.get(j))) {                // если найден повтор в списке строк
                    list.remove(j);                             // удалим повтор
                    article.incrementCounter();                 // а в объекте отметим повторение строки
                }
            }

            uniqueList.add(article);                            // сохраним объект со счетчиком в свой список
            //System.out.println(article.toString());           // вывод в формате: Article{name='chair', counter=3}
        }
        return uniqueList;
    }
}
