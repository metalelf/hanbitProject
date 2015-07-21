package member.service;

import java.sql.SQLException;

import member.dao.MemberDao;
import member.model.CheckRequest;
import member.model.MemberInfo;

public class MemberCheckHelper {

	// DAO ������ ���� ���� ����
	MemberDao memberDao;

	// DAO ������ ���� setter Method
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	/*public MemberInfo checkPassword(String st_password) throws SQLException,
			MemberNotFoundException, InvalidPasswordException {

		MemberInfo memberInfo = memberDao.selectByPassword(st_password);

		if (memberInfo == null) {
			throw new MemberNotFoundException("�ش� �����Ͱ� �������� ���� : "
					+ st_password);
		}

		if (!checkPassword(memberInfo.getSt_password(), st_password)) {
			throw new InvalidPasswordException("��ȣƲ��");
		}
		return memberInfo;
	}*/

	public MemberInfo checkExistsAndPassword(CheckRequest checkRequest)
			throws SQLException, MemberNotFoundException,
			InvalidPasswordException {

		MemberInfo memberInfo = memberDao.selectById(checkRequest.getSt_id());
		if (memberInfo == null) {
			throw new MemberNotFoundException("�ش� �����Ͱ� �������� ����: " + checkRequest.getSt_id());
		}
		if (!checkPassword(memberInfo.getSt_password(), checkRequest.getSt_password())) {
			throw new InvalidPasswordException("��ȣ Ʋ��");
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