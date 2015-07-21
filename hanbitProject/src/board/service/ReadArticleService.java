package board.service;

import java.sql.SQLException;

import board.dao.ArticleDao;
import board.model.Article;

public class ReadArticleService {
	
	// DAO 설정을 위한 변수 선언
	ArticleDao articleDao;

	// DAO 설정을 위한 setter Method
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	public Article readArticle(int articleId) throws ArticleNotFoundException {
		return selectArticle(articleId, true);
	}

	private Article selectArticle(int articleId, boolean increaseCount) throws ArticleNotFoundException {
		
		try{
			Article article = articleDao.selectById(articleId);
			if (article == null) {
				throw new ArticleNotFoundException("게시글이 존재하지 않음: " + articleId);
			}
			if (increaseCount) {
				articleDao.increaseReadCount(articleId);
				article.setReadCount(article.getReadCount() + 1);
			}
			return article;
		} catch (SQLException e) {
			throw new RuntimeException("DB 에러: " + e.getMessage(), e);
		} 
	}

	public Article getArticle(int articleId) throws ArticleNotFoundException {
		return selectArticle(articleId, false);
	}
	
}
