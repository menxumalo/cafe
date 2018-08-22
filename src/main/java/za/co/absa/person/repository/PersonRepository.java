package za.co.absa.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.absa.person.entity.Person;
@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

}
