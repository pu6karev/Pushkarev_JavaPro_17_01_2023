package ua.ithillel.lesson23;

import ua.ithillel.lesson22.Hero;
import ua.ithillel.lesson22.HeroDao;

import java.util.ArrayList;
import java.util.List;

public class DummyHeroDao implements HeroDao {

    private final List<Hero> db;

    public DummyHeroDao(List<Hero> db) {
        this.db = db;
    }

    @Override
    public List<Hero> findAll() {
        return db;
    }

    @Override
    public List<Hero> findByName(String name) {
        List<Hero> heroByName = new ArrayList<>();

        for (Hero h : db) {
            if(h.getName().equals(name)) {
                heroByName.add(h);
            }
        }
        return heroByName;
    }

    @Override
    public Hero findById(Long id) {
        return null;
    }

    @Override
    public void create(Hero hero) {
        db.add(hero);
    }

    @Override
    public void update(Hero hero) {
        int id = db.indexOf(hero);
        db.set(id, hero);
    }

    @Override
    public boolean delete(Long id) {
        Hero hero = db.get(id.intValue());
        return db.remove(hero);
    }
}
