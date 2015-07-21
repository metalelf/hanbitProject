package board.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import board.model.Article;

public class ArticleDao {

	private JdbcTemplate jdbcTemplate;

	public ArticleDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private String where(String select, int search){
		String keyword = "";
		
		if (search > 0) {
			
			if (search == 1) {
				keyword = " where title like '%" + select + "%'";
			}
			else if (search == 2) {
				keyword = " where content like '%" + select + "%'";
			}
			else {
				keyword = " where title like '%" + select + "%' or content like '%" + select + "%'";
			}
			
		}
		
		return keyword;
	}
	
	
	public int selectCount(String select, int search) throws SQLException {
		String keyword = where(select, search);
		return jdbcTemplate.queryForInt("select count(*) from article" + keyword);
	}
	
	//list
	public List<Article> select(String select, int search, int firstRow, int endRow) throws SQLException {
		String keyword = where(select, search);
		String sql = "select article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title, content from ( "
				+ "select rownum rnum, article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title, content from ("
				+ "select * from article a" + keyword + " order by a.sequence_no desc) where rownum <= ?"
				+ ") where rnum >= ? ";
		return jdbcTemplate.query(sql, new Object[] { endRow, firstRow }, new ArticleRowMapper());
	}

	//read
	public Article selectById(int articleId) throws SQLException {

		String sql = "select * from article  where article_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { articleId }, new ArticleRowMapper());
	}
	
	//Á¶È¸¼ö
	public void increaseReadCount(int articleId) throws SQLException {

		String sql = "update article set read_count = read_count + 1 where article_id = ?";
		jdbcTemplate.update(sql, new Object[] { articleId });
	}
	
	// insert
	public int insert(final Article article) throws SQLException {

		String sql = "insert into article "
				+ "(article_id, group_id, sequence_no, posting_date, read_count, "
				+ "writer_name, password, title, content) "
				+ "values (article_id_seq.nextval, ?, ?, ?, 0, ?, ?, ?, ?)";
		int insertedCount = jdbcTemplate.update(sql,
				new Object[] {
						article.getGroupId(),
						article.getSequenceNumber(),
						new Timestamp(article.getPostingDate().getTime()),
						article.getWriterName(),
						article.getPassword(),
						article.getTitle(),
						article.getContent() });
		if (insertedCount > 0) {
			int id = jdbcTemplate.queryForInt("select article_id_seq.CURRVAL from dual");
			article.setId(id);
		}
		return insertedCount;
	}
	
	//delete
	public void delete(int articleId) throws SQLException {

		String sql = "delete from article where article_id = ?";

		jdbcTemplate.update(sql, new Object[] { articleId });
	}
	
	//update
	public int update(Article article) throws SQLException {

		String sql = "update article set title = ?, content = ? where article_id = ?";
		return jdbcTemplate.update(sql, new Object[] { article.getTitle(),
				article.getContent(), article.getId() });
	}
	
	//reply
	public String selectLastSequenceNumber(String searchMaxSeqNum, String searchMinSeqNum) throws SQLException {
		
		String sql = "select min(sequence_no) from article where sequence_no < ? and sequence_no >= ?";
		
		String lastSequenceNum = jdbcTemplate.queryForObject(sql, new Object[] {
				searchMaxSeqNum, searchMinSeqNum }, String.class);
		if (lastSequenceNum == null) {
			return null;
		}
		return lastSequenceNum;
	}
	
}
