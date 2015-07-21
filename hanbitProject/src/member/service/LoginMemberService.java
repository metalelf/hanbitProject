package member.service;

import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;

import member.dao.MemberDao;
import member.model.LoginRequest;
import member.model.MemberInfo;

public class LoginMemberService {

	// DAO 설정을 위한 변수 선언
		MemberDao memberDao;

		// DAO 설정을 위한 setter Method
		public void setMemberDao(MemberDao memberDao) {
			this.memberDao = memberDao;
		}

	
	public MemberInfo login(LoginRequest loginRequest)throws SQLException, LoginException, EmptyResultDataAccessException{
		
		try{
			//int st_num = userInputInfo.getSt_num();
			String st_id = loginRequest.getSt_id();
			String st_password = loginRequest.getSt_password();
			
			
			MemberInfo dbInfo = memberDao.selectById(st_id);
			
			if(dbInfo.getSt_password().equals(st_password)){
				return dbInfo;
			}else{
				throw new LoginException();
			}
		}//try
		catch (SQLException e) {
			throw new LoginException();
		} catch(LoginException e){
			throw new LoginException();
		} catch(EmptyResultDataAccessException e){
			throw new LoginException();
		}
	}
}
		