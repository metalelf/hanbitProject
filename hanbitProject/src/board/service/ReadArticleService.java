package board.service;

import java.sql.SQLException;

import board.dao.ArticleDao;
import board.model.Article;

public class ReadArticleService {
	
	// DAO ������ ���� ���� ����
	ArticleDao articleDao;

	// DAO ������ ���� setter Method
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
				throw new ArticleNotFoundException("�Խñ��� �������� ����: " + articleId);
			}
			if (increaseCount) {
				articleDao.increaseReadCount(articleId);
				article.setReadCount(article.getReadCount() + 1);
			}
			return article;
		} catch (SQLException e) {
			throw new RuntimeException("DB ����: " + e.getMessage(), e);
		} 
	}

	public Article getArticle(int articleId) throws ArticleNotFoundException {
		return selectArticle(articleId, false);
	}
	
}