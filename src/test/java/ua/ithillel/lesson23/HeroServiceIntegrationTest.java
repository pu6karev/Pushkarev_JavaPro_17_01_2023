package ua.ithillel.lesson23;

import ua.ithillel.lesson22.Hero;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroServiceIntegrationTest {

    private HeroService target;
    List<Hero> heroList = new ArrayList<>();

    public void init(){
        heroList.add(new Hero("Cheburash", "-", "Black", "Sovietist", "Brown", 41, "Dovzhenko", "Brown", "Good", 5));
        heroList.add(new Hero("CrocoGena", "-", "Green", "Sovietist", "Green", 120, "MultUnion", "Green", "Good", 17));
        heroList.add(new Hero("DarthVader", "Male", "Grey", "Jedi", "Black", 185, "Lukas", "White", "Bad", 92));
        heroList.add(new Hero("Yoda", "-", "Brown", "Jedi", "-", 53, "Lukas", "Green", "Good", 22));
        heroList.add(new Hero("Alfred Pennyworth","Male","blue","Human","Black",178,"DC Comics","-","Good",72));
        heroList.add(new Hero("Ant-Man","Male","blue","Human","Blond",211,"Marvel Comics","-","Good",122));
        heroList.add(new Hero("Ariel","Female","purple","-","Pink",165,"Marvel Comics","-","Good",59));
        heroList.add(new Hero("Captain Planet","Male","red","God / Eternal","Green",-99,"Marvel Comics","-","Good",-99));
        heroList.add(new Hero("King Kong","Male","yellow","Animal","Black",30,"DC Comics","-","-",-99));
        target = HeroFabric.createService(heroList);
    }

    @Test
    void shouldReturnListOfHeroes() {
        init();
        List<HeroDto> heroDtos = target.getHeroes();

        assertEquals(heroDtos.size(), 9);
    }
}