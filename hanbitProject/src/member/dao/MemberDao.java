package member.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import member.model.MemberInfo;
import member.model.UpdateMemberRequest;

import org.springframework.jdbc.core.JdbcTemplate;

import cl.dao.BuyRowMapper;
import cl.model.BuyInfo;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);

	}

	// list + searchId/Pw
	public int selectCount(String st_tel, MemberInfo memberInfo)
			throws SQLException {

		String sql = "select count(*) from student where st_tel = ?";

		if (st_tel.equals(memberInfo.getSt_tel())) {
			return jdbcTemplate.queryForInt(sql, new Object[] { st_tel });
		} else {
			throw new SQLException();
		}
	}

	public List<MemberInfo> select(int firstRow, int endRow, String st_tel,
			MemberInfo memberInfo) throws SQLException {

		String sql = "select st_num, st_id, st_password, st_name, st_tel, st_address, st_email, join_date from ( "
				+ "select rownum rnum, st_num, st_id, st_password, st_name, st_tel, st_address, st_email, join_date from ("
				+ "select * from student a "
				+ "where a.st_tel = ? "
				+ "order by a.st_num desc) where rownum <= ?"
				+ ") where rnum >= ?";

		if (st_tel.equals(memberInfo.getSt_tel())) {
			return jdbcTemplate.query(sql, new Object[] { st_tel, endRow,
					firstRow }, new MemberRowMapper());
		} else {
			throw new SQLException();
		}

	}

	// insert
	public int insert(final MemberInfo memberInfo) throws SQLException {

		System.out.println(memberInfo.getSt_id()+":"+memberInfo.getSt_password()+":"+ memberInfo.getSt_name()+":"+memberInfo.getSt_tel()+":"+ memberInfo.getSt_address()+":"+	memberInfo.getSt_email()+":"+ new Timestamp(memberInfo.getJoin_date().getTime()));
		String sql = "insert into student (st_num, st_id, st_password, st_name, st_tel, st_address, st_email, join_date) values(student_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
		int insertedCount = jdbcTemplate.update(
				sql,
				new Object[] { memberInfo.getSt_id(),
						memberInfo.getSt_password(), memberInfo.getSt_name(),
						memberInfo.getSt_tel(), memberInfo.getSt_address(),
						memberInfo.getSt_email(),
						new Timestamp(memberInfo.getJoin_date().getTime()) }// Object[]
				);// jdbcTemplate.update()

		return insertedCount;

	}

	// id중복 검사
	public int idCheck(String st_id) throws SQLException {
		String sql = "select count(st_id) from student where st_id = ?";
		return jdbcTemplate.queryForInt(sql, new Object[] { st_id });
	}

	// login, 수정전 비밀번호 체크
	public MemberInfo selectById(String st_id) throws SQLException {

		String sql = "select * from student where st_id = ?";

		return jdbcTemplate.queryForObject(sql, new Object[] { st_id },
				new MemberRowMapper());

	}

	// id, login, 수정 전 비밀번호 찾기
	/*
	 * public MemberInfo selectIdPw(String st_tel) throws SQLException { String
	 * sql = "select st_id, st_password where st_tel = ?";
	 * 
	 * return jdbcTemplate.queryForObject(sql, new Object[] { st_tel }, new
	 * MemberRowMapper()); }
	 */

	// 회원정보 수정
	public int update(UpdateMemberRequest updateMemberRequest)
			throws SQLException {

		String sql = "update student set st_password = ?, st_tel = ?, st_address = ?, st_email = ? where st_id = ?";

		return jdbcTemplate.update(
				sql,
				new Object[] {updateMemberRequest.getSt_password(),
						updateMemberRequest.getSt_tel(),
						updateMemberRequest.getSt_address(),
						updateMemberRequest.getSt_email(),
						updateMemberRequest.getSt_id()});
	}

	// 회원 탈퇴
	public void delete(String st_id) throws SQLException {

		String sql = "delete from student where st_id = ?";

		jdbcTemplate.update(sql, new Object[] { st_id });
	}
	
	//my page 수강과목 가져오기
	public List<BuyInfo> getClass(String st_id) throws SQLException{
		String sql = "select * from buy where st_id =?";
		return jdbcTemplate.query(sql, new Object[]{st_id}, new BuyRowMapper());
	}

}
