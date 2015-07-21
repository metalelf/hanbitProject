package cl.model;

import java.util.Collections;
import java.util.List;

public class BuyListModel {
	private List<BuyInfo> buyInfoList;
	private int requestPage;
	private int totalPageCount;
	private int startRow;
	private int endRow;
	private String select;
	private int search;
	
	public BuyListModel() {
		this(Collections.<BuyInfo>emptyList(), 0, 0, 0, 0, null, 0);
	}
	
	public BuyListModel(List<BuyInfo> buyInfoList, int requestPageNumber,
			int totalPageCount, int startRow, int endRow, String select, int search) {
		this.buyInfoList = buyInfoList;
		this.requestPage = requestPageNumber;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endRow = endRow;
		this.select = select;
		this.search = search;
	}
	
	public List<BuyInfo> getBuyInfoList() {
		return buyInfoList;
	}
	
	public boolean isHasBuyInfo() {
		return ! buyInfoList.isEmpty();
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
