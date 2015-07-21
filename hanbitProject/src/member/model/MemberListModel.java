package member.model;

import java.util.Collections;
import java.util.List;

//ID/PW Ã£±â
public class MemberListModel {
	
	private List<MemberInfo> memberList;
	private int requestPage;
	private int totalPageCount;
	private int startRow;
	private int endRow;
	
	private String st_tel;

	public MemberListModel() {
		this(Collections.<MemberInfo>emptyList(), 0, 0, 0, 0, null);
	}
	
	public MemberListModel(List<MemberInfo> memberList, int requestPageNumber,
			int totalPageCount, int startRow, int endRow, String st_tel) {
		this.memberList = memberList;
		this.requestPage = requestPageNumber;
		this.totalPageCount = totalPageCount;
		this.startRow = startRow;
		this.endRow = endRow;
		this.st_tel = st_tel;
	}

	public List<MemberInfo> getMemberList() {
		return memberList;
	}
	
	public boolean isHasMemberInfo() {
		return ! memberList.isEmpty();
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
	
	public String getSt_tel(){
		return st_tel;
	}
	
}
