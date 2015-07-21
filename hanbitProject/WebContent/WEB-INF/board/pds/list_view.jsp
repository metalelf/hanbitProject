<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 1L);
%>
<html>
<head>
<title>자료실 목록</title>

<script>
	function LoginCheck() {

		alert("로그인 후 이용 가능합니다.");
		self.close();
		return false;
	}
</script>

</head>
<body>
	<div class="script_background">
		<div id="content_wrap">
			<h3>자료실</h3>
			<table border="1" class="entire_table">

				<c:if test="${listModel.totalPageCount > 0}">
					<tr>
						<td colspan="5">${listModel.startRow}-${listModel.endRow}
							[${listModel.requestPage}/${listModel.totalPageCount}]</td>
					</tr>
				</c:if>

				<tr>
					<td>번호</td>
					<td>파일명</td>
					<td>파일크기</td>
					<td>다운로드회수</td>
					<td>다운로드</td>
				</tr>

				<c:choose>
					<c:when test="${listModel.hasPdsItem == false}">
						<tr>
							<td colspan="5">게시글이 없습니다.</td>
						</tr>
					</c:when>

					<c:otherwise>

						<c:forEach var="item" items="${listModel.pdsItemList}">
							<tr>
								<td>${item.id}</td>
								<td>${item.fileName}</td>
								<td>${item.fileSize}</td>
								<td>${item.downloadCount}</td>
								<td><c:if test="${sessionId == null}">
										<a href="javascript:LoginCheck()">다운받기</a>
									</c:if> <c:if test="${sessionId != null}">
										<a href="download.do?id=${item.id}">다운받기</a>
									</c:if></td>
							</tr>
						</c:forEach>

						<tr>
							<td colspan="5"><c:if test="${beginPage > 10}">
									<a href="list.jsp?p=${beginPage-1}">이전</a>
								</c:if> <c:forEach var="pno" begin="${beginPage}" end="${endPage}">
									<a href="pds.do?p=${pno}">[${pno}]</a>
								</c:forEach> <c:if test="${endPage < listModel.totalPageCount}">
									<a href="list.jsp?p=${endPage + 1}">다음</a>
								</c:if></td>
						</tr>

					</c:otherwise>
				</c:choose>

				<tr>
					<td colspan="5"><a href="pds_upload.do">파일 첨부</a></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>