<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>���� ���</title>
<style>

</style>
</head>
<body>
<div class="script_background">
<div id="content_wrap">
<h3>���� ��û</h3>
<table border="1" class="entire_table">
	<c:if test="${list.totalPageCount > 0}">
	<tr>
		<td colspan="7">
		${list.startRow}-${list.endRow}
		[${list.requestPage}/${list.totalPageCount}]
		</td>
	</tr>
	</c:if>
	
	<tr>
		<td>���Ǹ�</td>
		<td>���� ������</td>
		<td>���� ������</td>
		<td>���Ƿ�</td>
		<td>���� �ð�</td>
		<td>�����̸�</td>
		<td>�����ο�</td>
	</tr>
	
<c:choose>
	<c:when test="${list.hasArticle == false}">
	<tr>
		<td colspan="7">
			�Խñ��� �����ϴ�.
		</td>
	</tr>
	</c:when>
	<c:otherwise>
	<c:forEach var="article" items="${list.clArticleList}">
		<tr>		
			<td>
				<a href="<c:url value="cl_read.do?page=${list.requestPage}&cl_num=${article.cl_num}"/>">
					${article.cl_name}
				</a>
			</td>
			<td>${article.cl_start_date}</td>
			<td>${article.cl_end_date}</td>
			<td>${article.cl_price}</td>
			<td>${article.cl_time}</td>
			<td>${article.te_name}</td>
			<td>${article.cl_current} / ${article.cl_max}</td>	
		</tr>
	</c:forEach>
	<tr>
		<td colspan="7">
		
		<c:if test="${beginPage > 10}">
			<a href="<c:url value="cl_list.do?page=${beginPage-1}"/>">����</a>
		</c:if>
		<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
		<a href="<c:url value="cl_list.do?page=${pno}" />">[${pno}]</a>
		</c:forEach>
		<c:if test="${endPage < list.totalPageCount}">
			<a href="<c:url value="cl_list.do?page=${endPage + 1}"/>">����</a>
		</c:if>
		</td>
	</tr>
	</c:otherwise>
</c:choose>
	
	<!-- <tr>
		<td colspan="7">
			<a href="cl_write.do">���ǵ��</a>
		</td>
	</tr>	 -->
</table>
<br/>
	<form action="<c:url value='/cl_list.do' />" method="post">
	�˻� : <input type="radio" name="search" value="1"> ���Ǹ�
	<input type="radio" name="search" value="2"> �����
	<input type="radio" name="search" value="3" checked="checked" > ���Ǹ� + ����� 
	<input type="text" name="select" size="20"/>
	<input type="submit" value="�˻�" />
	</form>
	<a href="cl_list.do"><input type="button" value="��Ϻ���" /></a>
	</div>
	</div>
</body>
</html>