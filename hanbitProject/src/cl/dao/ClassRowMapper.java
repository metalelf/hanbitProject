package cl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.model.ClwriteArticle;

public class ClassRowMapper implements RowMapper<ClwriteArticle> {
	
	@Override
	public ClwriteArticle mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ClwriteArticle clwriteArticle = new ClwriteArticle();
		clwriteArticle.setCl_num(rs.getInt("cl_num"));
		clwriteArticle.setCl_name(rs.getString("cl_name"));
		clwriteArticle.setCl_start_date(rs.getString("cl_start_date"));
		clwriteArticle.setCl_end_date(rs.getString("cl_end_date"));
		clwriteArticle.setCl_price(rs.getInt("cl_price"));
		clwriteArticle.setCl_time(rs.getString("cl_time"));
		clwriteArticle.setCl_max(rs.getInt("cl_max"));
		clwriteArticle.setCl_current(rs.getInt("cl_current"));
		clwriteArticle.setTe_num(rs.getInt("te_num"));
		clwriteArticle.setCl_content(rs.getString("cl_content"));
		
		return clwriteArticle;
	}

}
