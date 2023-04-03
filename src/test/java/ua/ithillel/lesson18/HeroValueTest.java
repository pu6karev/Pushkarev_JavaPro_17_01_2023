package ua.ithillel.lesson18;

import org.junit.jupiter.api.Test;
import ua.ithillel.Lesson18.HeroLombok;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroValueTest {


    @Test
    public void shouldGet(){
        HeroLombok hero = new HeroLombok("Ant-Man","Male","blue","Human","Blond",211,"Marvel Comics","-","Good",122);

        assertEquals("Ant-Man", hero.getName());
        assertEquals(211, hero.getHeight());
    }
}
