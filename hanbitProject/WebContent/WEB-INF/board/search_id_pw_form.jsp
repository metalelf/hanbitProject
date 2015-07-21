<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>아이디/비밀번호 찾기</title>
</head>
<body>
<div class="script_background">
<div id="content_wrap">
<h3>ID/PW 찾기</h3>

<spring:hasBindErrors name="check_command" />
<form:errors path="check_command" />


<form action = "<c:url value='check_id_pw.do' />" method ="POST">
연락처 : <input type ="text" name ="st_tel" size ="10" value="${check_command.st_tel}" />
<form:errors path="check_command.st_tel" />&nbsp;
<input type = "submit" value = "아이디/비밀번호 검색" />
</form>
</div>
</div>
</body>
</html>