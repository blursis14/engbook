package lee.engbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lee.engbook.member.Member;
import lee.engbook.member.MemberService;

@Service
public class AuthService {

	@Autowired
	private MemberService memberService; 
	
	@Transactional
	public AuthInfo authenticate(String id,String password) {
		Member member=memberService.findById(id);
		if(member==null) {
			throw new WrongIdPasswordException(); 
		}
		if(!member.getPassword().contentEquals(password)) {
			throw new WrongIdPasswordException();
		}
		return new AuthInfo(member.getId());
	}
}
