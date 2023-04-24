package ua.ithillel.lesson22;

import org.postgresql.ds.PGSimpleDataSource;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // --- создаем DataSource
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("dbHero");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");

        // --- создаем DAO
        HeroDao heroDao = new HeroDaoImpl(dataSource);

        // --- методы
        List<Hero> heroes = heroDao.findAll();
        for (Hero hero : heroes) System.out.println(hero);

        List<Hero> names = heroDao.findByName("Namor");
        for (Hero hero : names) System.out.println(hero);

        Hero hero = new Hero("Ant-Man3","Male","blue","Human","Blond",211,"Marvel Comics","-","Good",122);
        heroDao.create(hero);
        heroDao.update(hero);
        heroDao.delete(730L);
    }
}
