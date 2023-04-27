package ua.ithillel.lesson23;

import ua.ithillel.lesson22.Hero;
import ua.ithillel.lesson22.HeroDao;

import java.util.ArrayList;
import java.util.List;

public class HeroService {
    private final HeroDao heroDao;
    private final HeroMovieService heroMovieService;

    public HeroService(HeroDao heroDao, HeroMovieService heroMovieService) {
        this.heroDao = heroDao;
        this.heroMovieService = heroMovieService;
    }

    public List<HeroDto> getHeroes() {
        List<Hero> heroes = heroDao.findAll();
        List<HeroDto> listDto = new ArrayList<>();
        for (Hero hero : heroes) {
            HeroDto heroDto = new HeroDto.HeroBuilder()
                    .name(hero.getName())
                    .build();
            List<String> movies = heroMovieService.getPlayedIn(hero.getName());
            listDto.add(heroDto);
        }
        return listDto;
    }
}

