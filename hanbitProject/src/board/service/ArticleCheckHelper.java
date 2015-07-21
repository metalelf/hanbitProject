package board.service;

import java.sql.SQLException;

import board.dao.ArticleDao;
import board.model.Article;

public class ArticleCheckHelper {
	
	ArticleDao articleDao;
	
	public void setArticleDao(ArticleDao articleDao){
		this.articleDao = articleDao;
	}
	
	public Article checkExistsAndPassword(int articleId, String password) throws SQLException, ArticleNotFoundException, InvalidPasswordException {

		Article article = articleDao.selectById(articleId);
		if (article == null) {
			throw new ArticleNotFoundException(
					"게시글이 존재하지 않음: " + articleId);
		}
		if (!checkPassword(article.getPassword(), password)) {
			throw new InvalidPasswordException("암호 틀림");
		}
		return article;
	}
		
	private boolean checkPassword(String realPassword, String userInputPassword) {
		if (realPassword == null) {
			return false;
		}
		if (realPassword.length() == 0) {
			return false;
		}
		return realPassword.equals(userInputPassword);
	}
}
