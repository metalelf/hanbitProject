package cl.service;

import java.sql.SQLException;

import cl.dao.ClassDAO;
import cl.model.BuyDelete;

public class BuyDeleteService {
	
	ClassDAO classDAO;
	
	public void setClassDAO(ClassDAO classDAO){
		this.classDAO = classDAO;
	}
	
	public void delete(BuyDelete buyDelete) throws SQLException {
		classDAO.buydelete(buyDelete);
	}
	
}
