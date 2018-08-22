package za.co.absa.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.absa.person.PersonNotFoundException;
import za.co.absa.person.entity.Person;
import za.co.absa.person.repository.PersonRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Override
    public Collection<Person> retrieveAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person retrievePerson(Long id) throws PersonNotFoundException {
            Optional<Person> personOptional = personRepository.findById(id);
            if(personOptional.isPresent()) {
                return personOptional.get();
            }
            throw new PersonNotFoundException("Person not found id= " + id);
    }
    @Override
    public Long getCombinedAges() {
        Collection<Person> persons = personRepository.findAll();
        return persons.stream().mapToLong(p -> p.getAge()).sum();
    }

    @Override
    public void updatePerson(Person person) throws PersonNotFoundException{
        Optional<Person> savedPerson = personRepository.findById(person.getId());
        if(!savedPerson.isPresent()) {
            throw new PersonNotFoundException("Person not found id= " + person.getId());
        }
        personRepository.save(person);
    }


}
