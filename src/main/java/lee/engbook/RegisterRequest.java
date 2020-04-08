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
	private String email;
	
	@NotBlank(message="필수항목입니다.")
	@Size(min=4,max=16,message="비밀번호는 4자 이상 16자 이하입니다.")
	@Pattern(regexp="[a-zA-Z1-9]{6,12}", message = "비밀번호는 영어와 숫자를 포함해야 합니다.")
	private String password;
	
	@NotBlank(message="필수항목입니다.")
	private String confirmPassword;
	
	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
	}
}





















