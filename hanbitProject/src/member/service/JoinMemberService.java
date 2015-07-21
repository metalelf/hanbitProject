package member.service;

import java.sql.SQLException;
import java.util.Date;

import member.dao.MemberDao;
//import member.model.CheckRequest;
import member.model.MemberInfo;

public class JoinMemberService {

	// DAO ������ ���� ���� ����
	MemberDao memberDao;

	// DAO ������ ���� setter Method
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public MemberInfo join(MemberInfo memberInfo) throws SQLException{
		
		System.out.println("memberJoinService");
		memberInfo.setJoin_date(new Date());

		try {
			memberDao.insert(memberInfo);
			return memberInfo;

		} catch (SQLException e) {
			throw new RuntimeException("ȸ������ ����: " + e.getMessage(), e);
		}

	}

	public int IdCheck(String st_id){

		try {
			int searchedId = memberDao.idCheck(st_id);
			/*if(searchedId != 0){
				throw new RuntimeException("�ߺ��Ǵ� ���̵� ");
			}*/
			return searchedId;
			
		} catch (SQLException e) {
			throw new RuntimeException("���̵� �ߺ�üũ ���� : " + e.getMessage(), e);
		}
	}
}
	
	/*public MemberInfo checkExistsAndPassword(CheckRequest checkRequest)
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
	}*/

