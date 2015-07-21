package cl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.model.ClArticle;

public class ClArticleRowMapper implements RowMapper<ClArticle> {

	@Override
	public ClArticle mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ClArticle clArticle = new ClArticle();
		
		clArticle.setCl_num(rs.getInt("cl_num"));
		clArticle.setCl_name(rs.getString("cl_name"));
		clArticle.setCl_start_date(rs.getString("cl_start_date"));
		clArticle.setCl_end_date(rs.getString("cl_end_date"));
		clArticle.setCl_price(rs.getInt("cl_price"));
		clArticle.setCl_time(rs.getString("cl_time"));
		clArticle.setCl_content(rs.getString("cl_content"));
		clArticle.setCl_max(rs.getInt("cl_max"));
		clArticle.setCl_current(rs.getInt("cl_current"));
		
		clArticle.setTe_name(rs.getString("te_name"));
		clArticle.setTe_tel(rs.getString("te_tel"));
		
		return clArticle;
	}
	
}
