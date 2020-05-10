package lee.engbook.bookmark;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lee.engbook.AuthInfo;
import lee.engbook.member.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BookmarkRestController {

	@Autowired
	private BookmarkService service;
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/blist",method=RequestMethod.GET)
	public List<Bookmark> list(){
		return service.getList();
	}
	
	@RequestMapping(value="/badd/{pin}/{din}/{folder}",method=RequestMethod.GET)
	public List<Bookmark> add(@PathVariable int pin, @PathVariable int din,@PathVariable String folder){
		return service.add(pin, din, folder);
	}
	
	@PostMapping("/bookmark/delete")
	public void delete(@RequestBody HashMap<String,Object> param,HttpSession session){
		
		int din=(int)param.get("din");
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo");
		System.out.println(din);
		int pin=memberService.findPin(authInfo.getId()); //회원 pin알아내기
		
		service.delete(pin,din);
	}
	
	
	
}


















