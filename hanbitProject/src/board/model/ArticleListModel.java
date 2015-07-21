package board.model;

import java.util.Collections;
import java.util.List;

public class ArticleListModel {
	
	private List<Article> articleList;
	private int requestPage;
	private int totalPageCount;
	private int startRow;
	private int endRow;
	private String select;
	private int search;

	public ArticleListModel() {
		this(Collections.<Article>emptyList(), 0, 0, 0, 0, null, 0);
	}
	
	public ArticleListModel(List<Article> articleList, int requestPageNumber,
			int totalPageCount, int startRow, int endRow, String select, int search) {
		this.articleList = articleList;
		this.requestPage = requestPageNumber;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endRow = endRow;
		this.select = select;
		this.search = search;
	}

	public List<Article> getArticleList() {
		return articleList;
	}
	
	public boolean isHasArticle() {
		return ! articleList.isEmpty();
	}

	public int getRequestPage() {
		return requestPage;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}
	
	public String getSelect() {
		return select;
	}
	
	public int getSearch() {
		return search;
	}
	
}
