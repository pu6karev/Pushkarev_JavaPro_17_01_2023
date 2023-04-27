package ua.ithillel.lesson23;

import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import ua.ithillel.lesson22.*;
import ua.ithillel.lesson22.HeroDaoImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HeroServiceTest {

    private HeroDaoImpl heroDao;
    private HeroMovieService heroMovieService;
    private HeroService heroService;

    @Test
    public void testGetHeroes() {
        heroDao = Mockito.mock(HeroDaoImpl.class);
        heroMovieService = Mockito.mock(HeroMovieService.class);
        heroService = new HeroService(heroDao, heroMovieService);

        Mockito.when(heroDao.findAll()).thenReturn(List.of(
                Hero.builder().name("Ant-Man").build(),
                Hero.builder().name("KingKong").build()
        ));

        Mockito.when(heroMovieService.getPlayedIn("Ant-Man")).thenReturn(new ArrayList<>());
        Mockito.when(heroMovieService.getPlayedIn("KingKong")).thenReturn(new ArrayList<>());


        List<HeroDto> heroDtos = heroService.getHeroes();

        assertEquals(heroDtos.size(), 2);
        assertEquals(heroDao.findAll().size(), 2);
    }
}