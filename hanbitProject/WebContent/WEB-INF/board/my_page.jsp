<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>My Page</title>

<style>
tr:nth-child(2n) {
	background: #E9E9E9;
}
</style>

</head>

<body>
	<br />
	<div id="content_wrap">
		<h3>My Page</h3>
		<table width="450px">
			<tr>
				<td colspan="4">${sessionName}��ȯ���մϴ�.</td>
			</tr>
			<tr>
				<td colspan="4">ȸ������</td>
			</tr>
			<tr>
				<td>ID :</td>
				<td>${myInfo.st_id}</td>
			</tr>
			<tr>
				<td>�̸� :</td>
				<td>${myInfo.st_name}</td>
			</tr>
			<tr>
				<td>����ó :</td>
				<td>${myInfo.st_tel}</td>
			</tr>
			<tr>
				<td>�ּ� :</td>
				<td>${myInfo.st_address}</td>
			</tr>
			<tr>
				<td>E-MAIL :</td>
				<td>${myInfo.st_email}</td>
			</tr>
			<tr>
				<td>���� �������� ���� :</td>

				<td>
				<c:forEach var="classInfo" items="${classInfo}">
				${classInfo.cl_name}
						<form action="<c:url value='buy_delete.do' />" method="post">
							<input type="hidden" name="id" value="${sessionId}" /> <input
								type="hidden" name="buyclass" value="${classInfo.cl_name}" /> <input
								type="submit" value="�������" />
						</form>
						<br/>
						</c:forEach>
					</td>
			</tr>
				
		</table>
		<table width="450px">
			<tr>
				<td><a href="<c:url value='logout.do' />">�α׾ƿ�</a></td>
				<td><a href="list.do">����</a></td>
				<td><a
					href="<c:url value='check.do?st_id=${checkInfo.st_id}'/>">���� ����</a></td>
				<td><a
					href="<c:url value='deleteMember.do?st_id=${checkInfo.st_id}'/>">ȸ��Ż��</a>
				</td>
		</table>
	</div>
</body>
</html>