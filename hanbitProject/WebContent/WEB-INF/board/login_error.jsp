<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="exceptionType" value="${updateException.getClass().simpleName}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인</title>
</head>
<body>
	<div class="script_background">
			<div id="content_wrap">
	<br/>
	<br/>
		존재하지 않는 ID 또는 Password입니다.
		
	<a href="main.do">메인</a>
	<a href="join.do">회원가입</a>
	<a href="check_id_pw.do">ID/비밀번호 찾기</a>
		</div>
	</div>
</body>
</html>