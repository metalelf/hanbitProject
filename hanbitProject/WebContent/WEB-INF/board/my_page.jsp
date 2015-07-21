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
				<td colspan="4">${sessionName}님환영합니다.</td>
			</tr>
			<tr>
				<td colspan="4">회원정보</td>
			</tr>
			<tr>
				<td>ID :</td>
				<td>${myInfo.st_id}</td>
			</tr>
			<tr>
				<td>이름 :</td>
				<td>${myInfo.st_name}</td>
			</tr>
			<tr>
				<td>연락처 :</td>
				<td>${myInfo.st_tel}</td>
			</tr>
			<tr>
				<td>주소 :</td>
				<td>${myInfo.st_address}</td>
			</tr>
			<tr>
				<td>E-MAIL :</td>
				<td>${myInfo.st_email}</td>
			</tr>
			<tr>
				<td>현재 수강중인 과목 :</td>

				<td>
				<c:forEach var="classInfo" items="${classInfo}">
				${classInfo.cl_name}
						<form action="<c:url value='buy_delete.do' />" method="post">
							<input type="hidden" name="id" value="${sessionId}" /> <input
								type="hidden" name="buyclass" value="${classInfo.cl_name}" /> <input
								type="submit" value="수강취소" />
						</form>
						<br/>
						</c:forEach>
					</td>
			</tr>
				
		</table>
		<table width="450px">
			<tr>
				<td><a href="<c:url value='logout.do' />">로그아웃</a></td>
				<td><a href="list.do">메인</a></td>
				<td><a
					href="<c:url value='check.do?st_id=${checkInfo.st_id}'/>">정보 수정</a></td>
				<td><a
					href="<c:url value='deleteMember.do?st_id=${checkInfo.st_id}'/>">회원탈퇴</a>
				</td>
		</table>
	</div>
</body>
</html>