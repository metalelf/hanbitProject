package cl.service;

import java.sql.SQLException;
import java.util.List;

import cl.dao.ClassDAO;
import cl.model.ClwriteArticle;
import cl.model.TeacherInfo;

public class WriteService {
	
	ClassDAO classDAO;
		
	public void setClassDAO(ClassDAO classDAO){
		this.classDAO = classDAO;
	}
	
	public List<TeacherInfo> teacherRead() throws SQLException {
		
		try{
			List<TeacherInfo> teacherList = classDAO.teacher_select();
			return teacherList;
		}
		catch(SQLException e){
			throw new RuntimeException("DB ¿¡·¯: " + e.getMessage(), e);
		}
	}
	
	public void write (ClwriteArticle clwriteArticle) throws SQLException{
		classDAO.insert(clwriteArticle);
	}
}
