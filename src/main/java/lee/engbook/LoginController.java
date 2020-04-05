package lee.engbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String submit(HttpServletRequest request,HttpSession session){
		try {
			AuthInfo authInfo=authService.authenticate(request.getParameter("id"), request.getParameter("password"));
			session.setAttribute("authInfo", authInfo);
			return "main";
		}catch(WrongIdPasswordException e) {
			return "loginForm";
		}
		
		
		
	
	}
}
