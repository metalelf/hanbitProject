package cl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.model.BuyInfo;

public class BuyRowMapper implements RowMapper<BuyInfo> {
	
	@Override
	public BuyInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BuyInfo buyInfo = new BuyInfo();
		buyInfo.setBuy_num(rs.getInt("buy_num"));
		buyInfo.setSt_id(rs.getString("st_id"));
		buyInfo.setCl_num(rs.getInt("cl_num"));
		buyInfo.setCl_price(rs.getInt("cl_price"));
		buyInfo.setCl_name(rs.getString("cl_name"));
		buyInfo.setBuy_date(rs.getTimestamp("buy_date"));
		
		return buyInfo;
	}
}
