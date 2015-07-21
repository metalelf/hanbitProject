package cl.model;

import java.util.Collections;
import java.util.List;

public class ClArticleListModel {
	
	private List<ClArticle> clArticleList;
	private int requestPage;
	private int totalPageCount;
	private int startRow;
	private int endRow;
	private String select;
	private int search;

	public ClArticleListModel() {
		this(Collections.<ClArticle>emptyList(), 0, 0, 0, 0, null, 0);
	}
	
	public ClArticleListModel(List<ClArticle> clArticleList, int requestPageNumber,
			int totalPageCount, int startRow, int endRow, String select, int search) {
		this.clArticleList = clArticleList;
		this.requestPage = requestPageNumber;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endRow = endRow;
		this.select = select;
		this.search = search;
	}

	public List<ClArticle> getClArticleList() {
		return clArticleList;
	}
	
	public boolean isHasArticle() {
		return ! clArticleList.isEmpty();
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
