<%@ page contentType="text/html; charset = euc-kr" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Login Form</title>

<script>
	function login_input() {

		var log = document.login_form;

		if (log.st_id.value == "") {
			alert("���̵� �Է��ϼ���.");
			log.st_id.focus();
			return false;
		}

		if (log.st_password.value == "") {
			alert("��й�ȣ�� �Է��ϼ���.");
			log.st_password.focus();
			return false;
		}

		log.submit();
		return false;
	}
</script>

</head>

<body>
	<br />
	<spring:hasBindErrors name="login_command" />
	<form:errors path="login_command" />

	<c:if test="${sessionId == null}">
		<form name="login_form" action="<c:url value='login.do' />"
			method="POST" onsubmit="return login_input()">
			<div class="login_table">
				<table>
					<tr>
						<td colspan="2">Login</td>
					</tr>
					<tr>
						<td>���̵�: <input type="text" name="st_id" class="st_id"
							size="10" value="${login_command.st_id}" /> <form:errors
								path="login_command.st_id" />
						</td>
						<td rowspan="2"><input type="submit" value="�α���" /></td>
					</tr>
					<tr>
						<td>��ȣ: <input type="password" name="st_password"
							class="st_id" size="10" value="${login_command.st_password}" />
							<form:errors path="login_command.st_password" />
						</td>
					</tr>
					<tr>
						<td colspan="2"><a href="<c:url value='join.do' />"><h5>ȸ������</h5></a>
							<a href="<c:url value='check_id_pw.do' />"><h5>ID/��й�ȣ
									ã��</h5></a></td>
					</tr>
				</table>
			</div>
		</form>
	</c:if>
	<c:if test="${sessionId != null}">
		<div class="login_table">
			<table>
				<tr>
					<td>${sessionName}��ȯ���մϴ�.</td>
				</tr>

				<tr>
					<td><a href="<c:url value='my_page.do?st_id=${sessionId}'/>">����������</a>
						<br /> <a href="<c:url value='logout.do' />">�α׾ƿ�</a> <br /> <a
						href="<c:url value='check.do?st_id=${sessionId}'/>">���� ����</a> <br />
						<a href="<c:url value='deleteMember.do?st_id=${sessionId}'/>">ȸ��Ż��</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>

</body>
</html>