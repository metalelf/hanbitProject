<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="exceptionType" value="${updateException.getClass().simpleName}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�α���</title>
</head>
<body>
	<div class="script_background">
			<div id="content_wrap">
	<br/>
	<br/>
		�������� �ʴ� ID �Ǵ� Password�Դϴ�.
		
	<a href="main.do">����</a>
	<a href="join.do">ȸ������</a>
	<a href="check_id_pw.do">ID/��й�ȣ ã��</a>
		</div>
	</div>
</body>
</html>