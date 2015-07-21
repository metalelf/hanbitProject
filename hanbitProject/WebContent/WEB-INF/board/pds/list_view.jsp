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
<title>�ڷ�� ���</title>

<script>
	function LoginCheck() {

		alert("�α��� �� �̿� �����մϴ�.");
		self.close();
		return false;
	}
</script>

</head>
<body>
	<div class="script_background">
		<div id="content_wrap">
			<h3>�ڷ��</h3>
			<table border="1" class="entire_table">

				<c:if test="${listModel.totalPageCount > 0}">
					<tr>
						<td colspan="5">${listModel.startRow}-${listModel.endRow}
							[${listModel.requestPage}/${listModel.totalPageCount}]</td>
					</tr>
				</c:if>

				<tr>
					<td>��ȣ</td>
					<td>���ϸ�</td>
					<td>����ũ��</td>
					<td>�ٿ�ε�ȸ��</td>
					<td>�ٿ�ε�</td>
				</tr>

				<c:choose>
					<c:when test="${listModel.hasPdsItem == false}">
						<tr>
							<td colspan="5">�Խñ��� �����ϴ�.</td>
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
										<a href="javascript:LoginCheck()">�ٿ�ޱ�</a>
									</c:if> <c:if test="${sessionId != null}">
										<a href="download.do?id=${item.id}">�ٿ�ޱ�</a>
									</c:if></td>
							</tr>
						</c:forEach>

						<tr>
							<td colspan="5"><c:if test="${beginPage > 10}">
									<a href="list.jsp?p=${beginPage-1}">����</a>
								</c:if> <c:forEach var="pno" begin="${beginPage}" end="${endPage}">
									<a href="pds.do?p=${pno}">[${pno}]</a>
								</c:forEach> <c:if test="${endPage < listModel.totalPageCount}">
									<a href="list.jsp?p=${endPage + 1}">����</a>
								</c:if></td>
						</tr>

					</c:otherwise>
				</c:choose>

				<tr>
					<td colspan="5"><a href="pds_upload.do">���� ÷��</a></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>