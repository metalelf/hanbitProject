<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>����</title>
</head>
<body>

<%
String pageinfo = (String)session.getAttribute("sessionPage");
String cotextPath = request.getContextPath();
%>
    <% response.sendRedirect(cotextPath+pageinfo); %>
    <!-- /WEB-INF/view/index.jsp �� �̵��Ѵ�-->
</body>
</html>