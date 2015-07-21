<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>MAIN</title>
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
<h3>��������</h3>
<table border="1" class="entire_table">
	<c:if test="${list.totalPageCount > 0}">
	<tr>
		<td colspan="5">
		${list.startRow}-${list.endRow}
		[${list.requestPage}/${list.totalPageCount}]
		</td>
	</tr>
	</c:if>
	
	<tr>
		<td>�� ��ȣ</td>
		<!-- <td>�� ���� ��ȣ</td> -->
		<td>����</td>
		<td>�ۼ���</td>
		<td>�ۼ���</td>
		<td>��ȸ��</td>
	</tr>
	
<c:choose>
	<c:when test="${list.hasArticle == false}">
	<tr>
		<td colspan="5">
			�Խñ��� �����ϴ�.
		</td>
	</tr>
	</c:when>
	<c:otherwise>
	<c:forEach var="article" items="${list.articleList}">
	<tr>
		<td>${article.id}</td>
		<%-- <td>${article.sequenceNumber}</td> --%>
		<td>
			<%-- <c:if test="${article.level > 0}">
			<c:forEach begin="1" end="${article.level}">-</c:forEach>&gt;
			</c:if> --%>
			
			<c:if test="${sessionId == null}">
			<a href="javascript:LoginCheck()">${article.title}</a>
			</c:if>
			
			<c:if test="${sessionId != null}">
			<c:set var="query" value="articleId=${article.id}&page=${list.requestPage}"/>
			<a href="<c:url value="/board/read.do?${query}"/>">${article.title}</a>
			</c:if>
		</td>
		<td>${article.writerName}</td>
		<td>${article.postingDate}</td>
		<td>${article.readCount}</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="6">
		
		<c:if test="${beginPage > 10}">
			<a href="<c:url value="/board/list.do?page=${beginPage-1}&select=${list.select}&search=${list.search}"/>">����</a>
		</c:if>
		<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
		<a href="<c:url value="/board/list.do?page=${pno}&select=${list.select}&search=${list.search}" />">[${pno}]</a>
		</c:forEach>
		<c:if test="${endPage < list.totalPageCount}">
			<a href="<c:url value="/board/list.do?page=${endPage + 1}&select=${list.select}&search=${list.search}"/>">����</a>
		</c:if>
		</td>
	</tr>
	</c:otherwise>
</c:choose>
	
	<!-- <tr>
		<td colspan="6">
			<a href="write.do">�۾���</a>
		</td>
	</tr>	 -->
</table>
<br/>
	<form action="<c:url value='list.do' />" method="post">
	<input type="radio" name="search" value="1">����
	<input type="radio" name="search" value="2">�۳���
	<input type="radio" name="search" value="3" checked="checked">���� + �۳��� <br/>
	�˻�: <input type="text" name="select" size="20"/>
	<input type="submit" value="�˻�" />
</form>
</div>
</div>
</body>
</html>