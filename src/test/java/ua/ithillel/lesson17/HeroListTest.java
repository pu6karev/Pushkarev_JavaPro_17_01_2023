package ua.ithillel.lesson17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HeroListTest {

    HeroAction heroAct = new HeroAction();

    @BeforeEach
    public void init(){
        heroAct.add(new Hero("Cheburash", "-", "Black", "Sovietist", "Brown", 41, "Dovzhenko", "Brown", "Good", 5));
        heroAct.add(new Hero("CrocoGena", "-", "Green", "Sovietist", "Green", 120, "MultUnion", "Green", "Good", 17));
        heroAct.add(new Hero("DarthVader", "Male", "Grey", "Jedi", "Black", 185, "Lukas", "White", "Bad", 92));
        heroAct.add(new Hero("Yoda", "-", "Brown", "Jedi", "-", 53, "Lukas", "Green", "Good", 22));
        heroAct.add(new Hero("Alfred Pennyworth","Male","blue","Human","Black",178,"DC Comics","-","Good",72));
        heroAct.add(new Hero("Ant-Man","Male","blue","Human","Blond",211,"Marvel Comics","-","Good",122));
        heroAct.add(new Hero("Ariel","Female","purple","-","Pink",165,"Marvel Comics","-","Good",59));
        heroAct.add(new Hero("Captain Planet","Male","red","God / Eternal","Green",-99,"Marvel Comics","-","Good",-99));
        heroAct.add(new Hero("King Kong","Male","yellow","Animal","Black",30,"DC Comics","-","-",-99));
    }

    @Test
    void shouldAdd() {

        assertEquals(9, heroAct.getAll().size());
        assertEquals("Cheburash", heroAct.getAll().get(0).getName() ); // имя в первом элементе списка
        assertEquals("King Kong", heroAct.getAll().get(8).getName() ); // имя в последнем элементе списка
    }

    @Test
    void shouldGetAvgHeight() {
        // 41+120+185+53+178+211+165+30 = 983 (Captain Planet -99 отриц.исключаем)
        // 983/8 = 122.875 среднее по 8 героям
        assertEquals(122.875, heroAct.getAvgHeight());
    }

    @Test
    void shouldGetHighestName() {
        assertEquals("Ant-Man", heroAct.getHighestName());
    }

    @Test
    void shouldGetHeaviestName() {
        assertEquals("Ant-Man", heroAct.getHeaviestName());
    }

    @Test
    void shouldGroupGender() {
        Map<String, Long> mapExpected = new HashMap<>();
        mapExpected.put("Female", 1L);
        mapExpected.put("Male", 5L);
        mapExpected.put("-", 3L);

        Map<String, Long> mapActual = heroAct.groupGender();

        assertEquals(mapExpected, mapActual);
    }

    @Test
    void shouldGroupAlignment() {
        Map<String, Long> mapExpected = new HashMap<>();
        mapExpected.put("Good", 7L);
        mapExpected.put("Bad", 1L);
        mapExpected.put("-", 1L);

        Map<String, Long> mapActual = heroAct.groupAlignment();

        assertEquals(mapExpected, mapActual);
    }

    @Test
    void shouldGetPopularPublisher() {
        List<String> publisherExpected = new ArrayList<>();
        publisherExpected.add("Marvel Comics");     // кол-во 3
        publisherExpected.add("DC Comics");         // кол-во 2
        publisherExpected.add("Lukas");             // кол-во 2

        List<String> publisherActual = heroAct.getPopularPublisher(3);

        // --- данная функция без сортировки, поэтому порядок наименований издательств с одинаковым кол-вом не гарантирован
        // --- и не можем сравнить просто два списка
        assertEquals(publisherExpected.size(), publisherActual.size());
        assertEquals(publisherExpected.get(0), publisherActual.get(0));
    }

    @Test
    void shouldGetPopularHairColor() {
        List<String> hairColorExpected = new ArrayList<>();
        hairColorExpected.add("Black");         // кол-во 3
        hairColorExpected.add("Green");     // кол-во 2

        List<String> hairColorActual = heroAct.getPopularHairColor(2);

        assertEquals(hairColorExpected, hairColorActual);
    }

    @Test
    void shouldGetMostPopularEyeColor() {

        assertEquals("blue", heroAct.getMostPopularEyeColor());
    }
}