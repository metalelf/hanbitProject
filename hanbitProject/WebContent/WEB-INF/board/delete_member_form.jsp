<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>ȸ��Ż��</title>
</head>
<body>
	<br />
	<h3>ȸ��Ż��</h3>
		<div id="content_wrap">
		<spring:hasBindErrors name="delete_command" />
		<form:errors path="delete_command" />
		<form action="<c:url value='deleteMember.do' />" method="POST">
			<input type="hidden" name="st_id" value="${st_id}" />
			<table>
				<tr>
					<td>��й�ȣ: <input type="password" name="st_password"
						value="${delete_command.st_password}" /></td>
					<form:errors path="delete_command.st_password" />
					<td><input type="submit" value="����" /></td>
				</tr>
			</table>
		</form>
		</div>
</body>
</html>