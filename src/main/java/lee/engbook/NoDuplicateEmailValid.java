package lee.engbook;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=NoDuplicateEmailValidator.class)

public @interface NoDuplicateEmailValid {

	String message() default "이메일이 중복됩니다.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
