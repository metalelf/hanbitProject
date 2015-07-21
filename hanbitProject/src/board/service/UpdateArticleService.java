package board.service;

import java.sql.SQLException;

import board.dao.ArticleDao;
import board.model.Article;

public class UpdateArticleService {
	
	ArticleDao articleDao;
	
	public void setArticleDao(ArticleDao articleDao){
		this.articleDao = articleDao;
	}
	
	ArticleCheckHelper articleCheckHelper;

	public void setArticleCheckHelper(ArticleCheckHelper articleCheckHelper) {
		this.articleCheckHelper = articleCheckHelper;
	}

	public Article update(UpdateRequest updateRequest)
			throws ArticleNotFoundException, InvalidPasswordException {

		try {

			articleCheckHelper.checkExistsAndPassword(updateRequest.getArticleId(), updateRequest.getPassword());

			Article updatedArticle = new Article();
			updatedArticle.setId(updateRequest.getArticleId());
			updatedArticle.setTitle(updateRequest.getTitle());
			updatedArticle.setContent(updateRequest.getContent());

			int updateCount = articleDao.update(updatedArticle);
			if (updateCount == 0) {
				throw new ArticleNotFoundException("게시글이 존재하지 않음: "
						+ updateRequest.getArticleId());
			}

			Article article = articleDao.selectById(updateRequest
					.getArticleId());

			return article;
		} catch (SQLException e) {
			throw new RuntimeException("DB 에러: " + e.getMessage(), e);
		} catch (ArticleNotFoundException e) {
			throw e;
		} catch (InvalidPasswordException e) {
			throw e;
		}
	}
}
