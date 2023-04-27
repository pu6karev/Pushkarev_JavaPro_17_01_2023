package ua.ithillel.lesson23;

import java.util.List;
import java.util.Map;

public class HeroMovieService {
    private final Map<String, List<String>> heroMovies;

    public HeroMovieService(Map<String, List<String>> heroMovies) {
        this.heroMovies = heroMovies;
    }

    public List<String> getPlayedIn(String heroName) {
        return heroMovies.get(heroName);
    }
}