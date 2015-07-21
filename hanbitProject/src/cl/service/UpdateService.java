package cl.service;

import java.sql.SQLException;

import cl.dao.ClassDAO;
import cl.model.ClwriteArticle;

public class UpdateService {
	
	ClassDAO classDAO;
	
	public void setClassDAO(ClassDAO classDAO){
		this.classDAO = classDAO;
	}
	
	public ClwriteArticle readClwriteArticle(int cl_num) throws ClNotFoundException {
		
		try{
			ClwriteArticle clwriteArticle = classDAO.class_select(cl_num);
			if (clwriteArticle == null) {
				throw new ClNotFoundException("게시글이 존재하지 않음: " + cl_num);
			}
			return clwriteArticle;
		}
		catch (SQLException e) {
			throw new RuntimeException("DB 에러: " + e.getMessage(), e);
		} 
	}
	
	public void update(ClwriteArticle clwriteArticle) throws SQLException {
		System.out.println(clwriteArticle.getCl_name());
		classDAO.update(clwriteArticle);
	}
}
