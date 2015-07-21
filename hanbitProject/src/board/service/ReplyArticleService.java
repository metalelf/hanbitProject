package board.service;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;

import board.dao.ArticleDao;
import board.model.Article;

public class ReplyArticleService {
	
	// DAO 설정을 위한 변수 선언
	ArticleDao articleDao;

	// DAO 설정을 위한 setter Method
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	public Article reply(ReplyingRequest replyingRequest) throws ArticleNotFoundException, CannotReplyArticleException, LastChildAleadyExistsException {

		Article article = replyingRequest.toArticle();

		try {

			Article parent = articleDao.selectById(replyingRequest.getParentArticleId());

			try {
				checkParent(parent, replyingRequest.getParentArticleId());
			} catch (Exception e) {			
				if (e instanceof ArticleNotFoundException) {
					throw (ArticleNotFoundException) e;
				}
				
				else if (e instanceof CannotReplyArticleException) {
					throw (CannotReplyArticleException) e;
				}
				
				else if (e instanceof LastChildAleadyExistsException) {
					throw (LastChildAleadyExistsException) e;
				}
			}

			String searchMaxSeqNum = parent.getSequenceNumber();
			String searchMinSeqNum = getSearchMinSeqNum(parent);

			String lastChildSeq = articleDao.selectLastSequenceNumber(searchMaxSeqNum, searchMinSeqNum);

			String sequenceNumber = getSequenceNumber(parent, lastChildSeq);

			article.setGroupId(parent.getGroupId());
			article.setSequenceNumber(sequenceNumber);
			article.setPostingDate(new Date());

			int articleId = articleDao.insert(article);
			if (articleId == -1) {
				throw new RuntimeException("DB 삽입 실패: " + articleId);
			}
			//article.setId(articleId);
			return article;
		} catch (SQLException e) {			
			throw new RuntimeException("DB 에러: " + e.getMessage(), e);
		} 
	}

	private void checkParent(Article parent, int parentId) throws ArticleNotFoundException, CannotReplyArticleException {
		
		if (parent == null) {
			throw new ArticleNotFoundException("부모글이 존재하지 않음: " + parentId);
		}

		int parentLevel = parent.getLevel();
		if (parentLevel == 3) {
			throw new CannotReplyArticleException("마지막 레벨 글에는 답글을 달 수 없습니다:"
					+ parent.getId());
		}
	}

	private String getSearchMinSeqNum(Article parent) {
		String parentSeqNum = parent.getSequenceNumber();
		DecimalFormat decimalFormat = new DecimalFormat("0000000000000000");
		long parentSeqLongValue = Long.parseLong(parentSeqNum);
		long searchMinLongValue = 0;
		switch (parent.getLevel()) {
		case 0:
			searchMinLongValue = parentSeqLongValue / 1000000L * 1000000L;
			break;
		case 1:
			searchMinLongValue = parentSeqLongValue / 10000L * 10000L;
			break;
		case 2:
			searchMinLongValue = parentSeqLongValue / 100L * 100L;
			break;
		}
		return decimalFormat.format(searchMinLongValue);
	}

	private String getSequenceNumber(Article parent, String lastChildSeq) throws LastChildAleadyExistsException {
		long parentSeqLong = Long.parseLong(parent.getSequenceNumber());
		int parentLevel = parent.getLevel();

		long decUnit = 0;
		if (parentLevel == 0) {
			decUnit = 10000L;
		} else if (parentLevel == 1) {
			decUnit = 100L;
		} else if (parentLevel == 2) {
			decUnit = 1L;
		}

		String sequenceNumber = null;

		DecimalFormat decimalFormat = new DecimalFormat("0000000000000000");
		if (lastChildSeq == null) { // 답변글이 없음
			sequenceNumber = decimalFormat.format(parentSeqLong - decUnit);
		} else { // 답변글이 있음
			// 마지막 답변글인지 확인
			String orderOfLastChildSeq = null;
			if (parentLevel == 0) {
				orderOfLastChildSeq = lastChildSeq.substring(10, 12);
				sequenceNumber = lastChildSeq.substring(0, 12) + "9999";
			} 
			
			else if (parentLevel == 1) {
				orderOfLastChildSeq = lastChildSeq.substring(12, 14);
				sequenceNumber = lastChildSeq.substring(0, 14) + "99";
			} 
			
			else if (parentLevel == 2) {
				orderOfLastChildSeq = lastChildSeq.substring(14, 16);
				sequenceNumber = lastChildSeq;
			}
			
			if (orderOfLastChildSeq.equals("00")) {
				throw new LastChildAleadyExistsException("마지막 자식글이 이미 존재합니다:"
						+ lastChildSeq);
			}
			long seq = Long.parseLong(sequenceNumber) - decUnit;
			sequenceNumber = decimalFormat.format(seq);
		}
		return sequenceNumber;
	}
	
}
