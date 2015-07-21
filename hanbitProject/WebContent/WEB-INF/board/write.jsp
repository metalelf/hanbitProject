<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>게시글 작성</title></head>
<body>
<br/>
게시글을 등록했습니다.
<br/>
<a href="<c:url value='/board/list.do?page=1'/>">목록보기</a>
<a href="<c:url value='/board/read.do?articleId=${articleId}&page=1'/>">게시글 읽기</a>

</body>
</html>
