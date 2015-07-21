<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>강의 목록</title>
<style>

</style>
</head>
<body>
<div class="script_background">
<div id="content_wrap">
<h3>수강 신청</h3>
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
		<td>강의명</td>
		<td>강의 시작일</td>
		<td>강의 종료일</td>
		<td>강의료</td>
		<td>강의 시간</td>
		<td>강사이름</td>
		<td>수강인원</td>
	</tr>
	
<c:choose>
	<c:when test="${list.hasArticle == false}">
	<tr>
		<td colspan="7">
			게시글이 없습니다.
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
			<a href="<c:url value="cl_list.do?page=${beginPage-1}"/>">이전</a>
		</c:if>
		<c:forEach var="pno" begin="${beginPage}" end="${endPage}">
		<a href="<c:url value="cl_list.do?page=${pno}" />">[${pno}]</a>
		</c:forEach>
		<c:if test="${endPage < list.totalPageCount}">
			<a href="<c:url value="cl_list.do?page=${endPage + 1}"/>">다음</a>
		</c:if>
		</td>
	</tr>
	</c:otherwise>
</c:choose>
	
	<!-- <tr>
		<td colspan="7">
			<a href="cl_write.do">강의등록</a>
		</td>
	</tr>	 -->
</table>
<br/>
	<form action="<c:url value='/cl_list.do' />" method="post">
	검색 : <input type="radio" name="search" value="1"> 강의명
	<input type="radio" name="search" value="2"> 강사명
	<input type="radio" name="search" value="3" checked="checked" > 강의명 + 강사명 
	<input type="text" name="select" size="20"/>
	<input type="submit" value="검색" />
	</form>
	<a href="cl_list.do"><input type="button" value="목록보기" /></a>
	</div>
	</div>
</body>
</html>