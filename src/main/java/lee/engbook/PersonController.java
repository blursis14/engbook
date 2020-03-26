package lee.engbook;

import java.util.List;
import org.springframework.ui.ModelMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {

	@Autowired
	private PersonDao personDao;
	
	@RequestMapping("/")
	@ResponseBody
	public String test() {
		return "hello";
	}
	@RequestMapping("/add")
	@ResponseBody
	public Person add(String firstName,String lastName) {
		Person person=new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		personDao.save(person);
		return person;
	}
	@RequestMapping("/list")
	@ResponseBody
	public List<Person> list(ModelMap model){
		return (List<Person>) personDao.findAll();
	}
}
