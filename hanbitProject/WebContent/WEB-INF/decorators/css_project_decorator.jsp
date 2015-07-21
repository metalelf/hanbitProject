<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>�Ѻ���������<sitemesh:write property='title' /></title>
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

		<!-- ���� -->
		<sitemesh:write property='body' />
		<!-- //���� -->

		<!-- footer -->
		<jsp:include page="/WEB-INF/include/new_footer2.jsp" flush="false" />
		<!-- //footer -->
	</div>
</body>
</html>
