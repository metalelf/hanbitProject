package member.validator;

import member.model.CheckRequest;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class Up_Del_Validator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CheckRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "st_password", "required");
	}

}
