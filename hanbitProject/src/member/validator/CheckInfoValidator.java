package member.validator;

//import member.model.CheckRequest;
import member.model.MemberInfo;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CheckInfoValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MemberInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "st_tel", "required");
	}

}
