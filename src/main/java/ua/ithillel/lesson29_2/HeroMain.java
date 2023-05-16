package ua.ithillel.lesson29_2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.ithillel.lesson23.HeroService;

import javax.sql.DataSource;

public class HeroMain {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(HeroConfiguration.class);

         // --- получение бинов из контекста
        DataSource dataSource = context.getBean(DataSource.class);
        HeroService heroService = context.getBean(HeroService.class);

        System.out.println(heroService.getByName("Ant-Man"));
    }
}
