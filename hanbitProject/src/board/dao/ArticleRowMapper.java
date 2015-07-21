package board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import board.model.Article;

public class ArticleRowMapper implements RowMapper<Article>  {
	
	@Override
	public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Article article = new Article();
		article.setId(rs.getInt("article_id"));
		article.setGroupId(rs.getInt("group_id"));
		article.setSequenceNumber(rs.getString("sequence_no"));
		article.setPostingDate(rs.getTimestamp("posting_date"));
		article.setReadCount(rs.getInt("read_count"));
		article.setWriterName(rs.getString("writer_name"));
		article.setPassword(rs.getString("password"));
		article.setTitle(rs.getString("title"));
		article.setContent(rs.getString("content"));
		return article;
	}

}
