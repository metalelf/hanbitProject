package member.service;

import java.sql.SQLException;

import member.dao.MemberDao;
import member.model.MemberInfo;
import member.model.UpdateMemberRequest;

public class UpdateMemberService {

	// DAO 설정을 위한 변수 선언
	MemberDao memberDao;

	// DAO 설정을 위한 setter Method
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}


	public MemberInfo update(UpdateMemberRequest updateMemberRequest)
			throws InvalidPasswordException, MemberNotFoundException {

		try {

			/*MemberInfo updatedMember = new MemberInfo();
			updatedMember.setSt_password(updateMemberRequest.getSt_password());
			updatedMember.setSt_tel(updateMemberRequest.getSt_tel());
			updatedMember.setSt_address(updateMemberRequest.getSt_address());*/

			int updateCount = memberDao.update(updateMemberRequest);
			if (updateCount == 0) {
				throw new MemberNotFoundException("해당 회원이 존재하지 않습니다."
						+ updateMemberRequest.getSt_id());
			}

			MemberInfo memberInfo = memberDao.selectById(updateMemberRequest
					.getSt_id());

			return memberInfo;
		} catch (SQLException e) {
			throw new RuntimeException("DB 에러: " + e.getMessage(), e);
		} catch (MemberNotFoundException e) {
			throw e;
		}
	}
}
