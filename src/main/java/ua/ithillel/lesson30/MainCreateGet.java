package ua.ithillel.lesson30;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainCreateGet {
    public static void main( String[] args ) {
        Configuration configuration = new Configuration().addAnnotatedClass(HibernateHero.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {

            // --- получим из БД по ID=10 данные о герое
            HibernateHero heroInfo = session.get(HibernateHero.class, 10);
            System.out.println("...");
            System.out.println("getting hero name: " + heroInfo.getName() + ", publisher: " + heroInfo.getPublisher());

            // --- создадим нового героя, сохраним запись в БД и тут же выведем информацию
            var hero = new HibernateHero("Yoda", "-", "Brown", "Jedi", "-", 53, "Lukas", "Green", "Good", 22);
            var id = session.save(hero);
            System.out.println("new hero id=" + id + " name: " + hero.getName() + " race: " + hero.getRace());
        }
    }
}
