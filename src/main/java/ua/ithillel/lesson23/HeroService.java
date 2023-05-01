package ua.ithillel.lesson23;

import ua.ithillel.lesson22.Hero;
import ua.ithillel.lesson22.HeroDao;

import java.util.List;
import java.util.stream.Collectors;

public class HeroService {
    private final HeroDao heroDao;
    private HeroMovieService heroMovieService;

    public HeroService(HeroDao heroDao) {
        this.heroDao = heroDao;
    }

    public HeroService(HeroDao heroDao, HeroMovieService heroMovieService) {
        this.heroDao = heroDao;
        this.heroMovieService = heroMovieService;
    }

    public List<HeroDto> getHeroes() {
        return heroDao.findAll()
                .stream()
                .map(hero -> {
                    List<String> movies = heroMovieService.getPlayedIn(hero.getName());
                    return new HeroDto.HeroBuilder()
                            .name(hero.getName())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public List<Hero> getByName(String nameHero){
        return heroDao.findByName(nameHero);
    }

//        List<Hero> heroes = heroDao.findAll();
//        List<HeroDto> listDto = new ArrayList<>();
//        for (Hero hero : heroes) {
//            HeroDto heroDto = new HeroDto.HeroBuilder()
//                    .name(hero.getName())
//                    .build();
//            List<String> movies = heroMovieService.getPlayedIn(hero.getName());
//            listDto.add(heroDto);
//        }
//        return listDto;
//    }
}

