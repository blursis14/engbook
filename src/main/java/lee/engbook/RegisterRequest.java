package lee.engbook;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterRequest {

	@NotBlank(message="필수항목입니다.")
	@Size(min=4,max=16,message="아이디는 4자 이상 16자 이하입니다.")
	@NoDuplicateIdValid
	private String id;
	
	@NotBlank(message="필수항목입니다.")
	@Email(message="이메일 형식이 올바르지 않습니다.")
	@NoDuplicateEmailValid
	private String email;
	
	@NotBlank(message="필수항목입니다.")
	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&^])[A-Za-z\\d@$!%*#?&^]{8,}$",
	message = "비밀번호는 문자,숫자,특수문자를 포함해 8자 이상이어야 합니다.")
	private String password;
	
	@NotBlank(message="필수항목입니다.")
	private String confirmPassword;
	
	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
	}
}





















