package cl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cl.model.TeacherInfo;

public class TeacherRowMapper implements RowMapper<TeacherInfo> {
	
	@Override
	public TeacherInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		TeacherInfo teacherInfo = new TeacherInfo();
		
		teacherInfo.setTe_num(rs.getInt("te_num"));
		teacherInfo.setTe_name(rs.getString("te_name"));
		teacherInfo.setTe_id(rs.getString("te_id"));
		teacherInfo.setTe_password(rs.getString("te_password"));
		teacherInfo.setTe_tel(rs.getString("te_tel"));
		teacherInfo.setTe_address(rs.getString("te_address"));
		
		return teacherInfo;
	}
}
