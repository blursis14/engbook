package lee.engbook;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lee.engbook.member.MemberService;
import lee.engbook.sentence.SentenceService;

@Controller
public class HelloController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	SentenceService sentenceService;
	
	@RequestMapping("/hello")
	public String index(Model model) {
		model.addAttribute("name", "SpringBlog from Millky");
		return "hello";
	}
	@RequestMapping("/ajaxtest")
	public String ajaxtest() {
		return "ajaxtest";
	}
	
	@RequestMapping("/main")
	public String main() {
		
		return "main";
	}
	
	@RequestMapping("/needLogin")  
	public String needLogin(Model model) {
		model.addAttribute("exception", "로그인이 필요한 서비스입니다.");
		return "loginForm";
	}
	
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam(value="search-word",required=false)String str) {
		ModelAndView mav=new ModelAndView();
		
		System.out.println(str);
		
		try {
			sentenceService.buildIndex();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		mav.addObject("sentence", sentenceService.search(str));
		
		mav.setViewName("search");
		return mav;
	}
	
	
}