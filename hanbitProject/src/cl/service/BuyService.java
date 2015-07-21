package cl.service;

import java.sql.SQLException;
import java.util.Date;

import cl.dao.ClassDAO;
import cl.model.BuyInfo;
import cl.model.BuyRequest;

public class BuyService {
	
	ClassDAO classDAO;
	
	public void setClassDAO(ClassDAO classDAO){
		this.classDAO = classDAO;
	}

	public BuyInfo buy(BuyRequest buyRequest, String id) throws SQLException {
	
		BuyInfo buyInfo = buyRequest.toBuyInfo();
		
		buyInfo.setSt_id(id);
		buyInfo.setBuy_date(new Date());
		try {
			classDAO.classCurrent(buyRequest.getCl_num());
			classDAO.buy_insert(buyInfo);
		}
		catch (SQLException e) {			
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		}
		return buyInfo;
	}
	
}
