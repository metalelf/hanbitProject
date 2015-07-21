package cl.service;

import java.sql.SQLException;
import java.util.List;

import cl.dao.ClassDAO;
import cl.model.BuyInfo;
import cl.model.BuyListModel;

public class BuyListService {
	// DAO ������ ���� ���� ����
	ClassDAO classDAO;

	// DAO ������ ���� setter Method
	public void setClassDAO(ClassDAO classDAO) {
		this.classDAO = classDAO;
	}
	
	public static final int COUNT_PER_PAGE = 10;

	// LIST view �������� �����ϱ� ���� ��ü ����
	public BuyListModel getBuyList(int requestPageNumber, String select, int search) {
		
		try {
			// ����Ʈ�� ������ ��ȣ �ʱ�ȭ ( ��Ʈ�ѷ����� �ʱ�ȭ �۾��� ������ �ѹ� �� ó�� ���� )
			if (requestPageNumber < 0) {
				throw new IllegalArgumentException("page number < 0 : " + requestPageNumber);
			}

			// ��ü �Խù��� ����
			int totalArticleCount = classDAO.buyListCount(select, search);

			// �Խù��� ���� �� �� ��ü ����.
			if (totalArticleCount == 0) {
				return new BuyListModel();
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
			List<BuyInfo> buyInfoList = classDAO.buy_select(select, search, firstRow, endRow);

			BuyListModel buyListView = new BuyListModel(
					buyInfoList, requestPageNumber, totalPageCount, firstRow, endRow, select, search);
			return buyListView;
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