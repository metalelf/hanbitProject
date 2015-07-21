package member.service;

import java.sql.SQLException;
import java.util.List;

import member.dao.MemberDao;
import member.model.MemberInfo;
import member.model.MemberListModel;

public class ListMemberService {

	// DAO ������ ���� ���� ����
	MemberDao memberDao;

	// DAO ������ ���� setter Method
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public static final int COUNT_PER_PAGE = 1;

	// LIST view �������� �����ϱ� ���� ��ü ����
	public MemberListModel getMemberList(int requestPageNumber, String st_tel,
			MemberInfo memberInfo) {

		try {
			// ����Ʈ�� ������ ��ȣ �ʱ�ȭ ( ��Ʈ�ѷ����� �ʱ�ȭ �۾��� ������ �ѹ� �� ó�� ���� )
			if (requestPageNumber < 0) {
				throw new IllegalArgumentException("page number < 0 : "
						+ requestPageNumber);
			}

			// ��ü �Խù��� ����
			int totalMemberCount = memberDao.selectCount(st_tel, memberInfo);

			// �Խù��� ���� �� �� ��ü ����.
			if (totalMemberCount == 0) {
				return new MemberListModel();
			}

			// ����Ʈ ������ ����
			int totalPageCount = calculateTotalPageCount(totalMemberCount);

			// ��û �������� ���� �Խù� ��ȣ
			int firstRow = (requestPageNumber - 1) * COUNT_PER_PAGE + 1;
			// ��û �������� ������ �Խù� ��ȣ
			int endRow = firstRow + COUNT_PER_PAGE - 1;

			// ��ü �Խù��� ������ ������ �� ǥ�� �Խù� �������� ���� ��� : ��û �������� ������ �Խù� ��ȣ
			if (endRow > totalMemberCount) {
				endRow = totalMemberCount;
			}

			// ��������� ���� ��ü ����
			List<MemberInfo> memberList = memberDao.select(firstRow, endRow,
					st_tel, memberInfo);

			MemberListModel memberListView = new MemberListModel(memberList,
					requestPageNumber, totalPageCount, firstRow, endRow, st_tel);
			return memberListView;
		} catch (SQLException e) {
			throw new RuntimeException("DB ����: " + e.getMessage(), e);
		}
	}

	// ����Ʈ ������ ���� ��� �� ��ȯ �޼���
	private int calculateTotalPageCount(int totalMemberCount) {
		if (totalMemberCount == 0) {
			return 0;
		}
		int pageCount = totalMemberCount / COUNT_PER_PAGE;
		if (totalMemberCount % COUNT_PER_PAGE > 0) {
			pageCount++;
		}
		return pageCount;
	}

}