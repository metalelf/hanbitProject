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
			alert("아이디를 입력하세요.");
			log.st_id.focus();
			return false;
		}

		if (log.st_password.value == "") {
			alert("비밀번호를 입력하세요.");
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
						<td>아이디: <input type="text" name="st_id" class="st_id"
							size="10" value="${login_command.st_id}" /> <form:errors
								path="login_command.st_id" />
						</td>
						<td rowspan="2"><input type="submit" value="로그인" /></td>
					</tr>
					<tr>
						<td>암호: <input type="password" name="st_password"
							class="st_id" size="10" value="${login_command.st_password}" />
							<form:errors path="login_command.st_password" />
						</td>
					</tr>
					<tr>
						<td colspan="2"><a href="<c:url value='join.do' />"><h5>회원가입</h5></a>
							<a href="<c:url value='check_id_pw.do' />"><h5>ID/비밀번호
									찾기</h5></a></td>
					</tr>
				</table>
			</div>
		</form>
	</c:if>
	<c:if test="${sessionId != null}">
		<div class="login_table">
			<table>
				<tr>
					<td>${sessionName}님환영합니다.</td>
				</tr>

				<tr>
					<td><a href="<c:url value='my_page.do?st_id=${sessionId}'/>">마이페이지</a>
						<br /> <a href="<c:url value='logout.do' />">로그아웃</a> <br /> <a
						href="<c:url value='check.do?st_id=${sessionId}'/>">정보 수정</a> <br />
						<a href="<c:url value='deleteMember.do?st_id=${sessionId}'/>">회원탈퇴</a>
					</td>
				</tr>
			</table>
		</div>
	</c:if>

</body>
</html>