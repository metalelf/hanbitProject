<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>main</title>

</head>
<body>
<%
String cotextPath = request.getContextPath();
%>
    <% response.sendRedirect(cotextPath+"/board/main.do"); %>
    <!-- /WEB-INF/view/index.jsp 로 이동한다-->
</body>
</html>