package lee.engbook;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lee.engbook.folder.FolderService;
import lee.engbook.member.Member;
import lee.engbook.member.MemberService;

@Controller
public class HelloController {

	@Autowired
	FolderService folderService;
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
	
	@RequestMapping("/bookmark")
	public ModelAndView bookmark(HttpSession session) {
		ModelAndView mav=new ModelAndView();
		
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo"); //로그인정보 얻기
		
		int pin=memberService.findPin(authInfo.getId()); //사용자의 pin정보 찾기

		mav.addObject("folders",folderService.find(pin)); //사용자의 폴더리스트 저장
		
		mav.setViewName("bookmark");
		return mav;
	}
	
}