<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>MAIN</title>
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
<h3>공지사항</h3>
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
		<td>글 번호</td>
		<!-- <td>글 순서 번호</td> -->
		<td>제목</td>
		<td>작성자</td>
		<td>작성일</td>
		<td>조회수</td>
	</tr>
	
<c:choose>
	<c:when test="${list.hasArticle == false}">
	<tr>
		<td colspan="5">
			게시글이 없습니다.
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
			<a href="<c:url value="/board/list.do?page=${beginPage-1}&select=${list.select}&search=${list.search}"/>">이전</a>
		</c:if>
		<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
		<a href="<c:url value="/board/list.do?page=${pno}&select=${list.select}&search=${list.search}" />">[${pno}]</a>
		</c:forEach>
		<c:if test="${endPage < list.totalPageCount}">
			<a href="<c:url value="/board/list.do?page=${endPage + 1}&select=${list.select}&search=${list.search}"/>">다음</a>
		</c:if>
		</td>
	</tr>
	</c:otherwise>
</c:choose>
	
	<!-- <tr>
		<td colspan="6">
			<a href="write.do">글쓰기</a>
		</td>
	</tr>	 -->
</table>
<br/>
	<form action="<c:url value='list.do' />" method="post">
	<input type="radio" name="search" value="1">제목
	<input type="radio" name="search" value="2">글내용
	<input type="radio" name="search" value="3" checked="checked">제목 + 글내용 <br/>
	검색: <input type="text" name="select" size="20"/>
	<input type="submit" value="검색" />
</form>
</div>
</div>
</body>
</html>