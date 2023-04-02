package ua.ithillel.lesson18;

import org.junit.jupiter.api.Test;
import ua.ithillel.Lesson18.HeroBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HeroBuilderTest {


    @Test
    public void shouldGet(){
        //HeroLombok hero = new HeroLombok("Ant-Man","Male","blue","Human","Blond",211,"Marvel Comics","-","Good",122);

        HeroBuilder hero = HeroBuilder.builder()
                .name("Ant-Man")
                .eyeColor("blue")
                .build();

        assertEquals("Ant-Man", hero.getName());
        assertEquals("blue", hero.getEyeColor());
        assertNull(hero.getGender());
        assertEquals(0.0, hero.getHeight());
    }
}
