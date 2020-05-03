package lee.engbook.bookmark;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value="/bdelete/{din}",method=RequestMethod.GET)
	public List<Bookmark> delete(@PathVariable int din){
		return service.delete(din);
	}
	
	
	
}


















