package ua.ithillel.lesson23;

import java.util.List;

public class HeroDto {

    private final String name;
    private final List<String> movies;

    private HeroDto(HeroBuilder heroBuilder) {
        this.name = heroBuilder.name;
        this.movies = heroBuilder.movies;
    }

    public static class HeroBuilder{
        private String name;
        private List<String> movies;

        public HeroBuilder name(String name){
            this.name = name;
            return this;
        }

        public HeroBuilder movies(List<String> movies){
            this.movies = movies;
            return this;
        }

        public HeroDto build(){
            return new HeroDto(this);
        }
    }
}