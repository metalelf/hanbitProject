<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script>
	function LoginCheck() {

		alert("로그인 후 이용 가능합니다.");
		self.close();
		return false;
	}
</script>

</head>
<body>
<header id="main_header">
	<h1>한빛교육센터</h1>
</header>
<nav id="main_gnb">
	<ul class="left">
		<li><a href="<c:url value='/board/main.do'/>">HOME</a></li>
		<li><a href="<c:url value='/board/list.do?page=1'/>">공지사항</a></li>
		<li><a href="<c:url value='/board/cl_list.do?page=1'/>">수강신청</a></li>
		<li><a href="<c:url value='/board/pds.do'/>">자료실</a></li>
		<c:if test = "${sessionId == null}">
		<li><a href="javascript:LoginCheck()">수강내역</a></li>
		</c:if>
		<c:if test = "${sessionId != null}">
		<li><a href="<c:url value='/board/cl_buy_list.do?page=1'/>">수강내역</a></li>
		</c:if>
	</ul>
</nav>
</body>
</html>