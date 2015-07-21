package member.service;

import java.sql.SQLException;
import java.util.List;

import member.dao.MemberDao;
import member.model.MemberInfo;

import org.springframework.dao.EmptyResultDataAccessException;

import cl.model.BuyInfo;
import cl.service.ClNotFoundException;

public class ReadMemberService {

	// DAO ������ ���� ���� ����
	MemberDao memberDao;

	// DAO ������ ���� setter Method
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public MemberInfo readMember(String st_id) throws MemberNotFoundException {
		return selectMember(st_id);
	}

	private MemberInfo selectMember(String st_id)
			throws MemberNotFoundException {

		try {
			MemberInfo memberInfo = memberDao.selectById(st_id);
			if (memberInfo == null) {
				throw new MemberNotFoundException("�����Ͱ� �������� ����: " + st_id);
			}

			return memberInfo;
		} catch (SQLException e) {
			throw new RuntimeException("DB ����: " + e.getMessage(), e);
		}
	}
	
	public List<BuyInfo> selectClass(String st_id) throws ClNotFoundException, EmptyResultDataAccessException{
		
		List<BuyInfo> buyInfo;
		
		try{
			buyInfo = memberDao.getClass(st_id);
			
			return buyInfo;
		} catch(SQLException e){
			throw new RuntimeException("DB����: " + e.getMessage(), e);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

}