package member.validator;

import member.model.MemberInfo;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MemberInfoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberInfo.class.isAssignableFrom(clazz);
	}// Ư�� Ŭ������ �ٸ� �������̽��� �����߰ų� ����� �޾Ҵ��� ���� Ȯ��

	@Override
	public void validate(Object target, Errors errors) {
		MemberInfo memberInfo = (MemberInfo) target;

		if (memberInfo.getSt_id() == null
				|| memberInfo.getSt_id().trim().isEmpty()) {
			errors.rejectValue("st_id", "required");
		} // : �ʵ忡 ���� ���� �ڵ带 �߰�
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