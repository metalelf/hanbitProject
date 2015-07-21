package member.service;

import java.sql.SQLException;

import member.dao.MemberDao;
import member.model.CheckRequest;


public class DeleteMemberService {
	
	MemberDao memberDao;
	public void setMemberDao(MemberDao memberDao){
		this.memberDao = memberDao;
	}
	
	MemberCheckHelper memberCheckHelper;
	public void setMemberCheckHelper(MemberCheckHelper memberCheckHelper){
		this.memberCheckHelper = memberCheckHelper;
	}
	
	public void deleteMember(CheckRequest checkRequest)
			throws MemberNotFoundException, InvalidPasswordException {
		try {
			
			memberCheckHelper.checkExistsAndPassword(checkRequest);
			memberDao.delete(checkRequest.getSt_id());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (MemberNotFoundException e) {
			throw e;
		} catch (InvalidPasswordException e) {
			throw e;
		}
	} 
	
}
