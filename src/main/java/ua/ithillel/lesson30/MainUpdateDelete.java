package ua.ithillel.lesson30;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainUpdateDelete {

    public static void main( String[] args ) {
        Configuration configuration = new Configuration().addAnnotatedClass(HibernateHero.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            // --- обновление имени с id=743 в таблице БД
            HibernateHero hero = session.get(HibernateHero.class, 743L);
            hero.setName("Micky");

            // --- удаление записи с id=744 в таблице БД
            HibernateHero hero2 = session.get(HibernateHero.class, 744L);
            session.delete(hero2);

            session.getTransaction().commit();
        } catch (Exception e) {
            // Обработка исключения, например, логирование ошибки
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
