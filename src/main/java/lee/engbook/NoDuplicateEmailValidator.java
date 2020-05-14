package lee.engbook;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import lee.engbook.member.Member;
import lee.engbook.member.MemberService;

public class NoDuplicateEmailValidator implements ConstraintValidator<NoDuplicateEmailValid, String>{
	@Autowired
	private MemberService memberService;
	
	@Override
	public void initialize(NoDuplicateEmailValid constraintAnnotation) {}
	
	@Override
	public boolean isValid(String value,ConstraintValidatorContext context) {
		Member member=memberService.findByEmail(value);
		if(member==null)
			return true;
		else
			return false;
	}
}
