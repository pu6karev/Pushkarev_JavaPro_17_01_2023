package ua.ithillel.lesson27;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.ithillel.lesson23.HeroFabric;
import ua.ithillel.lesson23.HeroServiceDao;

import javax.sql.DataSource;

@SpringBootApplication
@Component
public class RestAppHero {
    public static void main(String[] args) {
        SpringApplication.run(RestAppHero.class, args);
    }

    @Bean
    public HeroServiceDao heroService(){
        return HeroFabric.createServiceRest(getDataSource());
    }

    public static DataSource getDataSource(){
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("dbHero");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }
}
