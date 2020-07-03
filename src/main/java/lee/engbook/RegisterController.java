package lee.engbook;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lee.engbook.member.MemberService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private AuthService authService;
	
	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("registerRequest",new RegisterRequest());
		return "register/form";
		
	}
	@PostMapping("/form")
	public String submit(@Valid RegisterRequest regReq,BindingResult result,HttpSession session,Model model) {
		if(result.hasErrors()) {
			return "register/form";
		}
		try {
			memberService.regist(regReq);
			AuthInfo authInfo=authService.authenticate(regReq.getId(), regReq.getPassword());
			session.setAttribute("authInfo", authInfo);
			return "register/succeed";
		}catch(WrongIdPasswordException ex) { //비번일치x
			model.addAttribute("isPasswordMatch","비밀번호가 일치하지 않습니다");
			System.out.println("불일치");
			return "register/form";
		}
		
	}
	
}
















