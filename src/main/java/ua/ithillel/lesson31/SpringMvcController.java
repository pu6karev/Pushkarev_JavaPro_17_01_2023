package ua.ithillel.lesson31;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.ithillel.lesson23.HeroServiceDao;
import ua.ithillel.lesson22.Hero;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/")
public class SpringMvcController {
    private final HeroServiceDao heroService;

    public SpringMvcController(HeroServiceDao heroService) {
        this.heroService = heroService;
    }

    @GetMapping("heroes")
    public String heroes(Model model){
        model.addAttribute("nameX", "Test_Name");
        model.addAttribute("heroes", heroService.getHeroes());
        return "index";
    }

    @GetMapping("hero/{id}")
    public String hero(Model model, @PathVariable Long id) {

        try {
            Hero hero = heroService.getById(id);
            model.addAttribute("heroes", hero);
        } catch (NoSuchElementException e) {
            throw new HeroNotFoundException("Hero not found, wrong id: " + id);
        }

        return "index";
    }

    @ExceptionHandler(HeroNotFoundException.class)
    public String handleHeroNotFoundException(HeroNotFoundException ex, Model model) {

        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}
