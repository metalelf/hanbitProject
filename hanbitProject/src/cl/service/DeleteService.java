package cl.service;

import java.sql.SQLException;

import cl.dao.ClassDAO;

public class DeleteService {
	
	ClassDAO classDAO;
	
	public void setClassDAO(ClassDAO classDAO){
		this.classDAO = classDAO;
	}
	
	public void delete(int cl_num) throws SQLException {
		classDAO.delete(cl_num);
	}
}	
