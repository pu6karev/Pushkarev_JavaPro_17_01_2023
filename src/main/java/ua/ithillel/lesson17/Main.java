package ua.ithillel.lesson17;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        String delimiter = ";";
        File file = new File("heroes_information.csv");

        // --- Из файла создадим список с массивами слов, т.е. шахматка всех слов в файле, с загаловком в 1-й строке
        // --- у Кинг-Конга в значении роста вместо точки задана запятая, из-за чего ошибка парсинга в double, заменим
        List<String[]> words = Files.lines(file.toPath())
                .map(s -> s.replace(",", "."))
                .map(line -> line.split(delimiter))
                .toList();

        // --- List<String[]> lines переводим в List<Map<String, String>>
        // --- первую строку с заголовком пропустим и далее преобразуем каждую строку в Map<>, используя
        // --- заголовки в качестве ключей, в качестве значения - содержимое из соответствующих столбцов.
        List<Map<String, String>> mapList = words.stream()
                .skip(1)
                .map(line -> {
                    Map<String, String> map = new HashMap<>();
                    for (int i = 0; i < line.length; i++) {
                        map.put(words.get(0)[i], line[i]);
                    }
                    return map;
                })
                .toList();


        // --- из List<Map<>> создадим список объектов Hero
        List<Hero> heroList = mapList.stream()
                .map(s -> new Hero(s.get("Name"), s.get("Gender"), s.get("Eye color"), s.get("Race"), s.get("Hair color"),
                        Double.parseDouble(s.get("Height")), s.get("Publisher"), s.get("Skin color"),
                        s.get("Alignment"), Integer.parseInt(s.get("Weight"))))
                .toList();

        System.out.println("Size hero list = " + heroList.size());

        // тестируем методы из пункта ДЗ 1
        HeroAction heroAct = new HeroAction(heroList);
        System.out.println("average height: " + heroAct.getAvgHeight());
        System.out.println("the tallest: " + heroAct.getHighestName());
        System.out.println("the heaviest: " + heroAct.getHeaviestName());
        System.out.println("gender counts: " + heroAct.groupGender());
        System.out.println("alignment counts: " + heroAct.groupAlignment());
        System.out.println("5 most popular publisher: " + heroAct.getPopularPublisher(5));
        System.out.println("3 most popular hair color: " + heroAct.getPopularHairColor(3));
        System.out.println("the most popular eye color: " + heroAct.getMostPopularEyeColor());


    }
}
