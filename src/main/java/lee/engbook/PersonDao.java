package lee.engbook;

import org.springframework.data.repository.CrudRepository;
import lee.engbook.Person;


public interface PersonDao extends CrudRepository<Person,Long> {

}
