<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head><title>�� �б�</title></head>
<body>
	<br/>
	<br/>
	<div id="content_wrap">
	<table>
	<tr>
		<td>����</td>
		<td>${article.title}</td>
	</tr>
	<tr>
		<td>�ۼ���</td>
		<td>${article.writerName}</td>
	</tr>
	<tr>
		<td>�ۼ���</td>
		<td><fmt:formatDate value="${article.postingDate}" 
			pattern="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<td>����</td>
		<td>
			<pre><c:out value="${article.content}" /></pre>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<a href="list.do?page=${param.page}">��Ϻ���</a>
		<%-- <a href="reply.do?parentId=${article.id}&page=${param.page}">�亯����</a>
		<a href="update.do?articleId=${article.id}&page=${param.page}">�����ϱ�</a>
		<a href="delete.do?articleId=${article.id}">�����ϱ�</a> --%>
		</td>
	</tr>
	</table>
	</div>
</body>
</html>