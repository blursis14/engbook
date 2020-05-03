package lee.engbook;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lee.engbook.member.Member;
import lee.engbook.member.MemberService;

@Controller
public class HelloController {

	@Autowired
	MemberService memberService;
	
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
	public String main(Model model) {
		//model.addAttribute("firstData",)
		return "main";
	}
	
	@RequestMapping("/needLogin")  
	public String needLogin(Model model) {
		model.addAttribute("exception", "로그인이 필요한 서비스입니다.");
		return "loginForm";
	}
	
	
	
}