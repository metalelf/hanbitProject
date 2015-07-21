<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>게시글 목록</title>
</head>
<body>
	<br/>
	<h3>검색 결과</h3>
	<c:choose>
		<c:when test="${list.hasMemberInfo == false}">
	
			해당 데이터가 존재하지 않습니다.
	</c:when>

		<c:otherwise>
			<c:forEach var="member" items="${list.memberList}">
	
		<b>아이디 : </b>${member.st_id}
		<br />
		<b>비밀번호 : </b>${member.st_password}
		
	</c:forEach>
		</c:otherwise>
	</c:choose>
	<br />

	<a href="login.do">로그인</a>
	<a href="main.do">메인으로</a>

</body>
</html>