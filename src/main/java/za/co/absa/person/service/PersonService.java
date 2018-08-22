package za.co.absa.person.service;

import za.co.absa.person.PersonNotFoundException;
import za.co.absa.person.entity.Person;

import java.util.Collection;

public interface PersonService {
    Collection<Person> retrieveAllPersons();

    void deletePerson(Long id);

    Person savePerson(Person person);

    Person retrievePerson(Long id) throws PersonNotFoundException;

    Long getCombinedAges();

    void updatePerson(Person person) throws PersonNotFoundException;
}
