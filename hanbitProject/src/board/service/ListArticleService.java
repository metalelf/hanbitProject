package board.service;

import java.sql.SQLException;
import java.util.List;

import board.dao.ArticleDao;
import board.model.Article;
import board.model.ArticleListModel;

public class ListArticleService {
	
	// DAO ������ ���� ���� ����
	ArticleDao articleDao;

	// DAO ������ ���� setter Method
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	public static final int COUNT_PER_PAGE = 10;

	// LIST view �������� �����ϱ� ���� ��ü ����
	public ArticleListModel getArticleList(int requestPageNumber, String select, int search) {
		
		try {
			// ����Ʈ�� ������ ��ȣ �ʱ�ȭ ( ��Ʈ�ѷ����� �ʱ�ȭ �۾��� ������ �ѹ� �� ó�� ���� )
			if (requestPageNumber < 0) {
				throw new IllegalArgumentException("page number < 0 : " + requestPageNumber);
			}

			// ��ü �Խù��� ����
			int totalArticleCount = articleDao.selectCount(select, search);

			// �Խù��� ���� �� �� ��ü ����.
			if (totalArticleCount == 0) {
				return new ArticleListModel();
			}

			// ����Ʈ ������ ����
			int totalPageCount = calculateTotalPageCount(totalArticleCount);

			// ��û �������� ���� �Խù� ��ȣ
			int firstRow = (requestPageNumber - 1) * COUNT_PER_PAGE + 1;
			// ��û �������� ������ �Խù� ��ȣ
			int endRow = firstRow + COUNT_PER_PAGE - 1;

			// ��ü �Խù��� ������ ������ �� ǥ�� �Խù� �������� ���� ��� : ��û �������� ������ �Խù� ��ȣ
			if (endRow > totalArticleCount) {
				endRow = totalArticleCount;
			}

			// ��������� ���� ��ü ����
			List<Article> articleList = articleDao.select(select, search, firstRow, endRow);

			ArticleListModel articleListView = new ArticleListModel(
					articleList, requestPageNumber, totalPageCount, firstRow, endRow, select, search);
			return articleListView;
		} catch (SQLException e) {
			throw new RuntimeException("DB ����: " + e.getMessage(), e);
		}
	}

	// ����Ʈ ������ ���� ��� �� ��ȯ �޼���
	private int calculateTotalPageCount(int totalArticleCount) {
		if (totalArticleCount == 0) {
			return 0;
		}
		int pageCount = totalArticleCount / COUNT_PER_PAGE;
		if (totalArticleCount % COUNT_PER_PAGE > 0) {
			pageCount++;
		}
		return pageCount;
	}

}