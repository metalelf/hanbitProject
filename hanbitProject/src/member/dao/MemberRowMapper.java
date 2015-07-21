package member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import member.model.MemberInfo;

import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper<MemberInfo> {

	@Override
	public MemberInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setSt_num(rs.getInt("st_num"));
		memberInfo.setSt_id(rs.getString("st_id"));
		memberInfo.setSt_password(rs.getString("st_password"));
		memberInfo.setSt_name(rs.getString("st_name"));
		memberInfo.setSt_tel(rs.getString("st_tel"));
		memberInfo.setSt_address(rs.getString("st_address"));
		memberInfo.setSt_email(rs.getString("st_email"));
		memberInfo.setJoin_date(rs.getTimestamp("join_date"));
		
		return memberInfo;
	}
}
