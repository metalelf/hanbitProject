package member.validator;


import member.model.UpdateMemberRequest;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UpdateValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdateMemberRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "st_id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "st_password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "st_tel", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "st_address", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "st_email", "required");
	}

}
