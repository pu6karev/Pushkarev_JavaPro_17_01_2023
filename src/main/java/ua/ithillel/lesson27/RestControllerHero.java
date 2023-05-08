package ua.ithillel.lesson27;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.lesson22.Hero;
import ua.ithillel.lesson23.HeroServiceDao;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestControllerHero {
    private final HeroServiceDao heroService;

    @GetMapping("/heroes")
    public List<Hero> getHeroes(){
        return heroService.getHeroes();
    }

    @GetMapping("/heroes/{id}")
    public Hero getById(@PathVariable("id") Long idHero){
        return heroService.getById(idHero);
    }

    @GetMapping("/heroes/name/{name}")
    public Hero getByName(@PathVariable("name") String nameHero){
        return heroService.getByName(nameHero);
    }

    @PostMapping("/heroes")
    public Hero createHero(@RequestBody String body){
        System.out.println("Body = " + body);
        return heroService.createHero();
    }

    @PutMapping("/heroes/{id}")
    public Hero updateHero(@PathVariable("id") Long idHero){
        return heroService.update();
    }

    @DeleteMapping("/heroes/{id}")
    public boolean deleteHero(@PathVariable("id") Long idHero){
        return heroService.delete(idHero);
    }

}
