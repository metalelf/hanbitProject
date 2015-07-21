<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>���� ���</title>

</head>
<body>
	<div class="script_background">
		<div id="content_wrap">
			<h3>��������</h3>
			<table border="1" class="entire_table">
				<c:if test="${list.totalPageCount > 0}">
					<tr>
						<td colspan="7">${list.startRow}-${list.endRow}
							[${list.requestPage}/${list.totalPageCount}]</td>
					</tr>
				</c:if>

				<tr>
					<td>���� ��ȣ</td>
					<td>�л� ID</td>
					<td>���� ��ȣ</td>
					<td>���Ƿ�</td>
					<td>���Ǹ�</td>
					<td>������</td>
				</tr>

				<c:choose>
					<c:when test="${list.hasBuyInfo == false}">
						<tr>
							<td colspan="6">�Խñ��� �����ϴ�.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="article" items="${list.buyInfoList}">
							<tr>
								<td>${article.buy_num}</td>
								<td>${article.st_id}</td>
								<td>${article.cl_num}</td>
								<td>${article.cl_price}</td>
								<td>${article.cl_name}</td>
								<td>${article.buy_date}</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="6"><c:if test="${beginPage > 10}">
									<a
										href="<c:url value="/board/cl_buy_list.do?page=${beginPage-1}&select=${list.select}&search=${list.search}"/>">����</a>
								</c:if> <c:forEach var="pno" begin="${beginPage}" end="${endPage}">
									<a
										href="<c:url value="/board/cl_buy_list.do?page=${pno}&select=${list.select}&search=${list.search}" />">[${pno}]</a>
								</c:forEach> <c:if test="${endPage < list.totalPageCount}">
									<a
										href="<c:url value="/board/cl_buy_list.do?page=${endPage + 1}&select=${list.select}&search=${list.search}"/>">����</a>
								</c:if></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			<br/><br/>
			<form action="<c:url value='cl_buy_list.do' />" method="post">
				�˻� : <input type="radio" name="search" value="1"> ���̵� <input
					type="radio" name="search" value="2"> ���� ��ȣ <input
					type="radio" name="search" value="3" checked="checked">
				���Ǹ� <input type="text" name="select" size="20" /> <input
					type="submit" value="�˻�" />
			</form>
			<a href="cl_list.do"><input type="button" value="��Ϻ���" /></a>
		</div>
	</div>
</body>
</html>