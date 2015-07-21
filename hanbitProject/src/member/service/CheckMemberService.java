package member.service;

import java.sql.SQLException;

import member.model.CheckRequest;



public class CheckMemberService {
	
		
		MemberCheckHelper memberCheckHelper;
		
		public void setMemberCheckHelper(MemberCheckHelper memberCheckHelper) {
			this.memberCheckHelper = memberCheckHelper;
		}
		

		public void checkMemeber(CheckRequest checkRequest)
				throws MemberNotFoundException, InvalidPasswordException, SQLException {
			try {
				memberCheckHelper.checkExistsAndPassword(checkRequest);
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} catch (MemberNotFoundException e) {
				throw e;
			} catch (InvalidPasswordException e) {
				throw e;
			}
		}

}
