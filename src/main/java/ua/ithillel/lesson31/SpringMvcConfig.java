package ua.ithillel.lesson31;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.ithillel.lesson23.HeroFabric;
import ua.ithillel.lesson23.HeroServiceDao;
import ua.ithillel.lesson27.RestAppHero;

import javax.sql.DataSource;

@Configuration
public class SpringMvcConfig {
    @Bean
    public HeroServiceDao heroService(){
        return HeroFabric.createServiceRest(getDataSource());
    }

    @Bean
    public DataSource getDataSource(){
        return RestAppHero.getDataSource();
    }
}
