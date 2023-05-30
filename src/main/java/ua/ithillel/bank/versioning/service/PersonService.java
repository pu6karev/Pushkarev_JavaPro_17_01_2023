package ua.ithillel.bank.versioning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ithillel.bank.versioning.reposytory.Person;
import ua.ithillel.bank.versioning.reposytory.PersonRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public PersonDto findPerson(String id) {
        return personRepository.findByUid(id).map(this::mapPerson).orElseThrow();
    }

    public List<PersonDto> findPersons() {
        return personRepository.findAll().stream()
                .map(this::mapPerson)
                .toList();
    }


    public PersonDto create(PersonDto person) {
        var newPerson = Person.builder()
                .uid(UUID.randomUUID().toString())
                .name(person.name())
                .email(person.email())
                .build();
        var savedPerson = personRepository.save(newPerson);
        return mapPerson(savedPerson);
    }

    public void deletePerson(String uid) {
        var person = personRepository.findByUid(uid).orElseThrow();
        personRepository.delete(person);
    }

    public PersonDto updatePerson(String uid, PersonDto person) {
        var personToUpdate = personRepository.findByUid(uid).orElseThrow();
        personToUpdate.setName(person.name());
        personToUpdate.setEmail(person.email());
        return mapPerson(personRepository.save(personToUpdate));
    }

    private PersonDto mapPerson(Person person) {
        return PersonDto.builder()
                .id(person.getUid())
                .name(person.getName())
                .email(person.getEmail())
                .build();
    }
}
