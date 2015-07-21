package board.service;

import java.sql.SQLException;

import board.dao.ArticleDao;

public class DeleteArticleService {
	
	ArticleDao articleDao;
	
	public void setArticleDao(ArticleDao articleDao){
		this.articleDao = articleDao;
	}
	
	ArticleCheckHelper articleCheckHelper;
	
	public void setArticleCheckHelper(ArticleCheckHelper articleCheckHelper) {
		this.articleCheckHelper = articleCheckHelper;
	}
	

	public void deleteArticle(DeleteRequest deleteRequest)
			throws ArticleNotFoundException, InvalidPasswordException {
		try {
			
			articleCheckHelper.checkExistsAndPassword(deleteRequest.getArticleId(),
					deleteRequest.getPassword());
			articleDao.delete(deleteRequest.getArticleId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ArticleNotFoundException e) {
			throw e;
		} catch (InvalidPasswordException e) {
			throw e;
		}
	}
	
}
