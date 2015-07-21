<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>결제 목록</title>

</head>
<body>
	<div class="script_background">
		<div id="content_wrap">
			<h3>수강내역</h3>
			<table border="1" class="entire_table">
				<c:if test="${list.totalPageCount > 0}">
					<tr>
						<td colspan="7">${list.startRow}-${list.endRow}
							[${list.requestPage}/${list.totalPageCount}]</td>
					</tr>
				</c:if>

				<tr>
					<td>결제 번호</td>
					<td>학생 ID</td>
					<td>강의 번호</td>
					<td>강의료</td>
					<td>강의명</td>
					<td>결제일</td>
				</tr>

				<c:choose>
					<c:when test="${list.hasBuyInfo == false}">
						<tr>
							<td colspan="6">게시글이 없습니다.</td>
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
										href="<c:url value="/board/cl_buy_list.do?page=${beginPage-1}&select=${list.select}&search=${list.search}"/>">이전</a>
								</c:if> <c:forEach var="pno" begin="${beginPage}" end="${endPage}">
									<a
										href="<c:url value="/board/cl_buy_list.do?page=${pno}&select=${list.select}&search=${list.search}" />">[${pno}]</a>
								</c:forEach> <c:if test="${endPage < list.totalPageCount}">
									<a
										href="<c:url value="/board/cl_buy_list.do?page=${endPage + 1}&select=${list.select}&search=${list.search}"/>">다음</a>
								</c:if></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			<br/><br/>
			<form action="<c:url value='cl_buy_list.do' />" method="post">
				검색 : <input type="radio" name="search" value="1"> 아이디 <input
					type="radio" name="search" value="2"> 결제 번호 <input
					type="radio" name="search" value="3" checked="checked">
				강의명 <input type="text" name="select" size="20" /> <input
					type="submit" value="검색" />
			</form>
			<a href="cl_list.do"><input type="button" value="목록보기" /></a>
		</div>
	</div>
</body>
</html>