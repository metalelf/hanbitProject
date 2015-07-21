package board.service;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;

import board.dao.ArticleDao;
import board.model.Article;

public class WriteArticleService {
	
	ArticleDao articleDao;
	
	public void setArticleDao(ArticleDao articleDao){
		this.articleDao = articleDao;
	}
	
	// Group ID 축출을 위한 클래스 객체 등록
	IdGenerator idGenerator;
	// Group ID 축출을 위한 클래스 객체 setter Method
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}

	public Article write(WritingRequest writingRequest)
			throws IdGenerationFailedException, SQLException {

		int groupId = idGenerator.generateNextId("article");

		Article article = writingRequest.toArticle();

		article.setGroupId(groupId);
		article.setPostingDate(new Date());
		DecimalFormat decimalFormat = new DecimalFormat("0000000000");
		article.setSequenceNumber(decimalFormat.format(groupId) + "999999");

		try {
			
			int articleId = articleDao.insert(article);
			if (articleId == -1) {			
				throw new RuntimeException("DB 처리 에러: " + articleId);
			}
			//article.setId(articleId);
			return article;
		} catch (SQLException e) {			
			throw new RuntimeException("DB 처리 에러: " + e.getMessage(), e);
		} 
	}
}
