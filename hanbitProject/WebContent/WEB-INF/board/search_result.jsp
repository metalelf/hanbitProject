<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>�Խñ� ���</title>
</head>
<body>
	<br/>
	<h3>�˻� ���</h3>
	<c:choose>
		<c:when test="${list.hasMemberInfo == false}">
	
			�ش� �����Ͱ� �������� �ʽ��ϴ�.
	</c:when>

		<c:otherwise>
			<c:forEach var="member" items="${list.memberList}">
	
		<b>���̵� : </b>${member.st_id}
		<br />
		<b>��й�ȣ : </b>${member.st_password}
		
	</c:forEach>
		</c:otherwise>
	</c:choose>
	<br />

	<a href="login.do">�α���</a>
	<a href="main.do">��������</a>

</body>
</html>