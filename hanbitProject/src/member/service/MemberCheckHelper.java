package member.service;

import java.sql.SQLException;

import member.dao.MemberDao;
import member.model.CheckRequest;
import member.model.MemberInfo;

public class MemberCheckHelper {

	// DAO 설정을 위한 변수 선언
	MemberDao memberDao;

	// DAO 설정을 위한 setter Method
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	/*public MemberInfo checkPassword(String st_password) throws SQLException,
			MemberNotFoundException, InvalidPasswordException {

		MemberInfo memberInfo = memberDao.selectByPassword(st_password);

		if (memberInfo == null) {
			throw new MemberNotFoundException("해당 데이터가 존재하지 않음 : "
					+ st_password);
		}

		if (!checkPassword(memberInfo.getSt_password(), st_password)) {
			throw new InvalidPasswordException("암호틀림");
		}
		return memberInfo;
	}*/

	public MemberInfo checkExistsAndPassword(CheckRequest checkRequest)
			throws SQLException, MemberNotFoundException,
			InvalidPasswordException {

		MemberInfo memberInfo = memberDao.selectById(checkRequest.getSt_id());
		if (memberInfo == null) {
			throw new MemberNotFoundException("해당 데이터가 존재하지 않음: " + checkRequest.getSt_id());
		}
		if (!checkPassword(memberInfo.getSt_password(), checkRequest.getSt_password())) {
			throw new InvalidPasswordException("암호 틀림");
		}
		return memberInfo;
	}

	private boolean checkPassword(String realPassword, String userInputPassword) {
		if (realPassword == null) {
			return false;
		}
		if (realPassword.length() == 0) {
			return false;
		}
		return realPassword.equals(userInputPassword);
	}

}
