package ua.ithillel.lesson27;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.ithillel.lesson23.HeroFabric;
import ua.ithillel.lesson23.HeroServiceDao;

import javax.sql.DataSource;

@SpringBootApplication
public class RestAppHero {
    public static void main(String[] args) {
        SpringApplication.run(RestAppHero.class, args);
    }

    @Bean
    public HeroServiceDao heroService(){
        return HeroFabric.createServiceRest(getDataSource());
    }

    private DataSource getDataSource(){
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("dbHero");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }
}
