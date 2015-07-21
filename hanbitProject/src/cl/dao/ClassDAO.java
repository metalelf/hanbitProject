package cl.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import cl.model.BuyDelete;
import cl.model.BuyInfo;
import cl.model.ClArticle;
import cl.model.ClwriteArticle;
import cl.model.TeacherInfo;

public class ClassDAO {
	
	private JdbcTemplate jdbcTemplate;

	public ClassDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private String where(String select, int search){
		String keyword = "";
		
		if (search > 0) {
			
			if (search == 1) {
				keyword = " where cl_name like '%" + select + "%'";
			}
			else if (search == 2) {
				keyword = " where te_name like '%" + select + "%'";
			}
			else {
				keyword = " where cl_name like '%" + select + "%' or te_name like '%" + select + "%'";
			}
			
		}
		
		return keyword;
	}
	
	//list 갯수 구함
	public int selectCount(String select, int search) throws SQLException {
		String keyword = where(select, search);
		return jdbcTemplate.queryForInt("select count(*) from class_te_view" + keyword);
	}
	
	//list
	public List<ClArticle> select(String select, int search, int firstRow, int endRow) throws SQLException {
		String keyword = where(select, search);
		String sql = "select cl_num, cl_name, cl_start_date, cl_end_date, cl_price, cl_time, cl_content, te_name, te_tel, cl_max, cl_current from ( "
				+ "select rownum rnum, cl_num, cl_name, cl_start_date, cl_end_date, cl_price, cl_time, cl_content, te_name, te_tel, cl_max, cl_current from ("
				+ "select * from class_te_view v" + keyword + " order by v.cl_num desc) where rownum <= ?"
				+ ") where rnum >= ? ";
		return jdbcTemplate.query(sql, new Object[] { endRow, firstRow }, new ClArticleRowMapper());
	}
	
	//read
	public ClArticle selectById(int cl_num) throws SQLException {

		String sql = "select * from class_te_view  where cl_num = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { cl_num }, new ClArticleRowMapper());
	}
	
	public int selectbuy(String sessionId) throws SQLException {
		String sql = "select count(*) from buy where st_id = ?";
		return jdbcTemplate.queryForInt(sql, new Object[] {sessionId});
	}
	
	//write
	public int insert(final ClwriteArticle clwriteArticle) throws SQLException {
		String sql = "insert into class "
				+ "(cl_num, cl_name, cl_start_date, cl_end_date, cl_price, cl_time, te_num, cl_content, cl_max, cl_current)"
				+ "values (class_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
		int insertedCount = jdbcTemplate.update(sql,
				new Object[] {
				clwriteArticle.getCl_name(),
				clwriteArticle.getCl_start_date(),
				clwriteArticle.getCl_end_date(),
				clwriteArticle.getCl_price(),
				clwriteArticle.getCl_time(),
				clwriteArticle.getTe_num(),
				clwriteArticle.getCl_content(),
				clwriteArticle.getCl_max()
		});
		return insertedCount;
	}
	
	//선생님 List
	public List<TeacherInfo> teacher_select() throws SQLException {
		String sql = "select * from teacher";
		return jdbcTemplate.query(sql, new TeacherRowMapper());
	}
	
	//delete
	public void delete(int cl_num) throws SQLException {

		String sql = "delete from class where cl_num = ?";

		jdbcTemplate.update(sql, new Object[] { cl_num });
	}
	
	//update
	public ClwriteArticle class_select(int cl_num) throws SQLException {
		
		String sql = "select * from class where cl_num = ?";
		
		return jdbcTemplate.queryForObject(sql, new Object[] { cl_num }, new ClassRowMapper());		
	}
	
	//update
	public int update(ClwriteArticle clwriteArticle) throws SQLException {

		String sql = "update class set cl_name = ?, cl_start_date = ?, cl_end_date = ?, cl_price = ?, cl_time = ?, te_num = ?, cl_content = ?, cl_max = ? where cl_num = ?";
		return jdbcTemplate.update(sql, new Object[] { 
				clwriteArticle.getCl_name(),
				clwriteArticle.getCl_start_date(), 
				clwriteArticle.getCl_end_date(),
				clwriteArticle.getCl_price(),
				clwriteArticle.getCl_time(),
				clwriteArticle.getTe_num(),
				clwriteArticle.getCl_content(),
				clwriteArticle.getCl_max(),
				clwriteArticle.getCl_num()
				});
	}
	
	//buy_insert
	public int buy_insert(final BuyInfo buyInfo) throws SQLException {
		String sql = "insert into buy (buy_num, st_id, cl_num, cl_price, cl_name, buy_date)"
				+ "values (buy_seq.nextval, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql,
				new Object[] {
					buyInfo.getSt_id(),
					buyInfo.getCl_num(),
					buyInfo.getCl_price(),
					buyInfo.getCl_name(),
					buyInfo.getBuy_date() });
	}
	
	//강의테이블 현재인원 변경
	public void classCurrent(int cl_num) throws SQLException {
		String sql = "update class set cl_current = cl_current + 1 where cl_num = ?";
		jdbcTemplate.update(sql, new Object[] { cl_num });
	}
	
	private String buy_where(String select, int search){
		String keyword = "";
		
		if (search > 0) {
			
			if (search == 1) {
				keyword = " where st_id like '%" + select + "%'";
			}
			else if (search == 2) {
				keyword = " where buy_num like '%" + select + "%'";
			}
			else {
				keyword = " where cl_name like '%" + select + "%'";
			}
			
		}
		
		return keyword;
	}
	
	
	public int buyListCount(String select, int search) throws SQLException {
		String keyword = buy_where(select, search);
		return jdbcTemplate.queryForInt("select count(*) from buy" + keyword);
	}
	
	//buy_list
	public List<BuyInfo> buy_select(String select, int search, int firstRow, int endRow) throws SQLException {
		String keyword = buy_where(select, search);
		String sql = "select buy_num, st_id, cl_num, cl_name, cl_price, buy_date from ( "
				+ "select rownum rnum, buy_num, st_id, cl_num, cl_name, cl_price, buy_date from ("
				+ "select * from buy b" + keyword + " order by b.cl_num desc) where rownum <= ?"
				+ ") where rnum >= ? ";
		return jdbcTemplate.query(sql, new Object[] { endRow, firstRow }, new BuyRowMapper());
	}
	
	//buy_delete
	public void buydelete(BuyDelete buyDelete) throws SQLException {
		String sql = "delete from buy where st_id = ? and cl_name = ?";
		jdbcTemplate.update(sql, new Object[] {buyDelete.getId(), buyDelete.getBuyclass()});
	}
	
	//중복 신청 불가
	public int classCheck(String st_id, int cl_num) throws SQLException{
		String sql = "select count(*) from buy where st_id =? and cl_num =?";
		return jdbcTemplate.queryForInt(sql, new Object[]{st_id, cl_num});
	}
}
