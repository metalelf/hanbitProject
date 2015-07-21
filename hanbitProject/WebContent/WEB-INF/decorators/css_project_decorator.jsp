<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>한빛교육센터<sitemesh:write property='title' /></title>
<%-- <link href="${pageContext.request.contextPath}/include/css/style2.css" rel="stylesheet" /> --%>
<link href="../include/css/style2.css" rel="stylesheet" />
<sitemesh:write property='head' />
</head>

<body>
	<!-- top -->
	<jsp:include page="/WEB-INF/include/new_top2.jsp" flush="false" />
	<!-- //top -->

	<div id="wrap">
		<!-- left -->
		<jsp:include page="/WEB-INF/include/new_left2.jsp" flush="false" />
		<!-- //left -->

		<!-- 내용 -->
		<sitemesh:write property='body' />
		<!-- //내용 -->

		<!-- footer -->
		<jsp:include page="/WEB-INF/include/new_footer2.jsp" flush="false" />
		<!-- //footer -->
	</div>
</body>
</html>
