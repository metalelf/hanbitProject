package cl.service;

import java.sql.SQLException;

import cl.dao.ClassDAO;
import cl.model.ClArticle;

public class ReadService {
	
	// DAO ������ ���� ���� ����
	ClassDAO classDAO;

	// DAO ������ ���� setter Method
	public void setClassDAO(ClassDAO classDAO) {
		this.classDAO = classDAO;
	}
	
	public int selectbuy(String sessionId) throws SQLException{
		int num = 0;
		try {
			num = classDAO.selectbuy(sessionId);
			return num;
		}
		catch (SQLException e) {
			return num;
		}
	}
		
	public ClArticle readClArticle(int cl_num) throws ClNotFoundException {
			
		try{
			ClArticle clarticle = classDAO.selectById(cl_num);
			if (clarticle == null) {
				throw new ClNotFoundException("�Խñ��� �������� ����: " + cl_num);
			}
			return clarticle;
		}
		catch (SQLException e) {
			throw new RuntimeException("DB ����: " + e.getMessage(), e);
		} 
	}
	
	public int clCheck(String st_id, int cl_num) throws SQLException{
		
		int cl = 0;
		try{
			cl = classDAO.classCheck(st_id, cl_num);
			return cl;
		}catch(Exception e){
			return cl;
		}
		
	}
}