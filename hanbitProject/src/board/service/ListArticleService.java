package board.service;

import java.sql.SQLException;
import java.util.List;

import board.dao.ArticleDao;
import board.model.Article;
import board.model.ArticleListModel;

public class ListArticleService {
	
	// DAO 설정을 위한 변수 선언
	ArticleDao articleDao;

	// DAO 설정을 위한 setter Method
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	public static final int COUNT_PER_PAGE = 10;

	// LIST view 페이지를 구성하기 위한 객체 전달
	public ArticleListModel getArticleList(int requestPageNumber, String select, int search) {
		
		try {
			// 리스트의 페이지 번호 초기화 ( 컨트롤러에서 초기화 작업을 하지만 한번 더 처리 해줌 )
			if (requestPageNumber < 0) {
				throw new IllegalArgumentException("page number < 0 : " + requestPageNumber);
			}

			// 전체 게시물의 개수
			int totalArticleCount = articleDao.selectCount(select, search);

			// 게시물이 없을 때 빈 객체 전달.
			if (totalArticleCount == 0) {
				return new ArticleListModel();
			}

			// 리스트 페이지 개수
			int totalPageCount = calculateTotalPageCount(totalArticleCount);

			// 요청 페이지의 시작 게시물 번호
			int firstRow = (requestPageNumber - 1) * COUNT_PER_PAGE + 1;
			// 요청 페이지의 마지막 게시물 번호
			int endRow = firstRow + COUNT_PER_PAGE - 1;

			// 전체 게시물의 개수가 페이지 당 표현 게시물 개수보다 작을 경우 : 요청 페이지의 마지막 게시물 번호
			if (endRow > totalArticleCount) {
				endRow = totalArticleCount;
			}

			// 결과전달을 위한 객체 생성
			List<Article> articleList = articleDao.select(select, search, firstRow, endRow);

			ArticleListModel articleListView = new ArticleListModel(
					articleList, requestPageNumber, totalPageCount, firstRow, endRow, select, search);
			return articleListView;
		} catch (SQLException e) {
			throw new RuntimeException("DB 에러: " + e.getMessage(), e);
		}
	}

	// 리스트 페이지 개수 계산 후 반환 메서드
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
