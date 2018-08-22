package za.co.absa.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import za.co.absa.person.PersonNotFoundException;
import za.co.absa.person.entity.Person;
import za.co.absa.person.repository.PersonRepository;
import za.co.absa.person.service.PersonService;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("persons")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Value("${api_man_username}")
    private String username;
    @Autowired
    private Environment env;
    @GetMapping
    public Collection<Person> getAllPersons() {
        System.out.println("username is " + username);
        System.out.println("username from environment is " + env.getProperty("api_man_username"));
        return personService.retrieveAllPersons();
    }

    @GetMapping("combinedAge")
    public Long getCombinedAges() {
        return personService.getCombinedAges();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") Long id) throws PersonNotFoundException{
        return personService.retrievePerson(id);
    }

    @PostMapping
    public ResponseEntity<Object> createPerson(@RequestBody Person person) {
        Person savedPerson = personService.savePerson(person);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPerson.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePerson(@RequestBody Person person, @PathVariable("id") Long id) throws PersonNotFoundException{
        person.setId(id);
        personService.savePerson(person);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable("id") Long id){
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
