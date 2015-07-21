<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head><title>글 읽기</title></head>
<body>
	<br/>
	<br/>
	<div id="content_wrap">
	<table>
	<tr>
		<td>제목</td>
		<td>${article.title}</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${article.writerName}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td><fmt:formatDate value="${article.postingDate}" 
			pattern="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<pre><c:out value="${article.content}" /></pre>
		</td>
	</tr>
	<tr>
		<td colspan="2">
		<a href="list.do?page=${param.page}">목록보기</a>
		<%-- <a href="reply.do?parentId=${article.id}&page=${param.page}">답변쓰기</a>
		<a href="update.do?articleId=${article.id}&page=${param.page}">수정하기</a>
		<a href="delete.do?articleId=${article.id}">삭제하기</a> --%>
		</td>
	</tr>
	</table>
	</div>
</body>
</html>