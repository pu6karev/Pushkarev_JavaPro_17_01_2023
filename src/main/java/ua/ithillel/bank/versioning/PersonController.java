package ua.ithillel.bank.versioning;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.bank.versioning.service.PersonDto;
import ua.ithillel.bank.versioning.service.PersonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/persons")
public class PersonController {

    private PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/{id}")
    public PersonDto getPerson(@PathVariable("id") String id){
        return personService.findPerson(id);
    }

    @GetMapping
    public List<PersonDto> getPersons(){
        return personService.findPersons();
    }

    @PostMapping
    public PersonDto createPerson(@RequestBody PersonDto person){
        return personService.create(person);
    }

    @PutMapping("/{id}")
    public PersonDto updatePerson(@PathVariable("id") String id, @RequestBody PersonDto person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") String id) {
        personService.deletePerson(id);
    }
}
