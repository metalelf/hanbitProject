<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<html>
<head>
<title>암호 입력</title>
</head>

<body>

	<br/>
	<div id="content_wrap">
	<h3>회원정보 변경</h3>
	<spring:hasBindErrors name="checkPw_command" />
		<form:errors path="checkPw_command" />
		<form action="<c:url value='check.do' />" method="post">
			<input type="hidden" name="st_id" value="${param.st_id}" />
			<table>
				<tr>
					<td>비밀번호를 입력하세요. <br /></td>
				</tr>
				<tr>
					<td><input type="password" name="st_password"
						value="${checkPw_command.st_password}" /> 
						<form:errors path="checkPw_command.st_password" /></td>
				
					<td><input type="submit" value="정보수정" /></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>