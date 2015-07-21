package member.validator;

import member.model.MemberInfo;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MemberInfoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberInfo.class.isAssignableFrom(clazz);
	}// 특정 클래스가 다른 인터페이스를 구현했거나 상속을 받았는지 여부 확인

	@Override
	public void validate(Object target, Errors errors) {
		MemberInfo memberInfo = (MemberInfo) target;

		if (memberInfo.getSt_id() == null
				|| memberInfo.getSt_id().trim().isEmpty()) {
			errors.rejectValue("st_id", "required");
		} // : 필드에 대한 에러 코드를 추가
		if (memberInfo.getSt_password() == null
				|| memberInfo.getSt_password().trim().isEmpty()) {
			errors.rejectValue("st_password", "required");
		}
		if (memberInfo.getSt_name() == null
				|| memberInfo.getSt_name().trim().isEmpty()) {
			errors.rejectValue("st_name", "required");
		}
		if (memberInfo.getSt_tel() == null
				|| memberInfo.getSt_tel().trim().isEmpty()) {
			errors.rejectValue("st_tel", "required");
		}
		if (memberInfo.getSt_email() == null
				|| memberInfo.getSt_email().trim().isEmpty()) {
			errors.rejectValue("st_email", "required");
		}
		//if
		
	}//validate
}
