package lee.engbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AuthService authService;
		
	@GetMapping
	public String form() {
		return "loginForm";
	}
	@PostMapping
	public String submit(HttpServletRequest request,HttpSession session,Model model){
		try {
			AuthInfo authInfo=authService.authenticate(request.getParameter("id"), request.getParameter("password"));
			session.setAttribute("authInfo", authInfo);
			model.addAttribute("exception", "");
			return "main";
		}catch(WrongIdPasswordException e) {
			model.addAttribute("exception", "존재하지 않는 아이디거나,아이디와 비밀번호가 일치하지 않습니다.");
			return "loginForm";
		}
		
		
		
	
	}
}
