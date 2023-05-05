package ua.ithillel.lesson23;
import ua.ithillel.lesson22.Hero;
import ua.ithillel.lesson22.HeroDao;
import ua.ithillel.lesson22.HeroDaoImpl;

import javax.sql.DataSource;
import java.util.*;

public class HeroFabric {

    public static HeroService createService(DataSource dataSource) {
        HeroDao heroDao = new HeroDaoImpl(dataSource);
        Map<String, List<String>> heroMovies = new HashMap<>();
        // --- заполнение heroMovies списками фильмов для каждого героя
        HeroMovieService heroMovieService = new HeroMovieService(heroMovies);
        return new HeroService(heroDao, heroMovieService);
    }

    public static HeroService createService(List<Hero> heroes) {
        HeroDao heroDao = new DummyHeroDao(heroes);
        HeroMovieService heroMovieService = new HeroMovieService(new HashMap<>());
        return new HeroService(heroDao, heroMovieService);
    }

    public static HeroService createServiceDao(DataSource dataSource) {
        HeroDao heroDao = new HeroDaoImpl(dataSource);
        return new HeroService(heroDao);
    }

}
