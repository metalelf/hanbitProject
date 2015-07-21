package member.validator;

import member.model.LoginRequest;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class LoginCommandValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "st_id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "st_password", "required");
	}

}// class
