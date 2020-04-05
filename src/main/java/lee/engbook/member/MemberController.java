package lee.engbook.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {
	
	@Autowired 
	private MemberService service;
	
	@RequestMapping(value="/mlist",method=RequestMethod.GET)
	public List list(){
		return service.getList();
	}
	
	@RequestMapping(value="/madd/{id}/{password}/{email}/{regType}",method=RequestMethod.GET)
	public List<Member> add(@PathVariable String id,@PathVariable String password,@PathVariable String email,@PathVariable String regType){
		return service.add(id, password, email, regType);
	}
	@RequestMapping(value="/mdelete/{id}",method=RequestMethod.GET)
	public List<Member> delete(@PathVariable String id){
		return service.delete(id);
	}
	
	@RequestMapping(value="/mfind/{id}",method=RequestMethod.GET)
	public Member find(@PathVariable String id) {
		return service.find(id);
	}
	

}
