package ua.ithillel.lesson27;


import ua.ithillel.lesson22.Hero;
import ua.ithillel.lesson23.HeroServiceDao;

public class Main {
    public static void main(String[] args) {
        RestAppHero restAppHero = new RestAppHero();
        HeroServiceDao heroServiceDao = restAppHero.heroService();

        Hero hero = heroServiceDao.getByName("Ajax");
        System.out.println(hero);

        Hero heroById = heroServiceDao.getById(2L);
        System.out.println(heroById);

        heroServiceDao.createHero();
    }
}
