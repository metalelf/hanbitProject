<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���̵��ߺ�üũ</title>

<script>
	//////////////////���̵� �ߺ��˻� �� �ش� ���̵� ������ ID�Է�â���� ���� + ��й�ȣ �Է� â���� Ŀ�� �̵�
	function sendId(st_id){
 	 	alert(st_id);
 	 	//�˾�â�� ������ ȣ���� ȭ��(member.jsp)���� ����
 	 	
 	 	opener.join_form.st_id.value=st_id;
 	 	opener.join_form.st_password.focus();
 	 	//�ش� �˾�â�� �ݴ´�!(�ڱ��ڽ�)
 	 	self.close();
 	}//sendId(id)

	////////////////////���� �Է� �� ȸ������ ��ư�� ������ �����ϴ� script
	function checkSubmit() {

		//���̵��� üũ 4~12,���� ���� _ - ^ ���ڷ� ���ۺҰ�
		var id = document.check_id_form.st_id.value;
		id = Space_All(id);
		if (id == null || id.length<4 ||id.length>20) {
			alert("���̵�� 4~20�� ������ ���� �Է�!!");
			return;
		}
		if (CheckChar(id) == false) {
			alert("���̵�� ����,���ڸ� ���� ���������ϳ� ���ڷ� ������ �Ұ�");
			return;
		}
		/////////////////�Է��� ������ ���� ���ǵ��� ������ ��� submit
		check_id_form.submit();

	}

	/////////////////////////////����˻�
	function Space_All(str) {
		var index, len;

		while (true) {
			index = str.indexOf(" ");
			// ������ ������ �����մϴ�.
			if (index == -1)
				break;
			// ���ڿ� ���̸� ���մϴ�.
			len = str.length;
			// ������ �߶���ϴ�.
			str = str.substring(0, index) + str.substring((index + 1), len);
		}

		return str;
	}

	// str�� ��� �ҹ��ڿ����ϰ� ù���ڴ� �����̾�� �Ѵ�. 
	// ������ 0~9, -, _, ^�� ����Ѵ�. 
	function CheckChar(str) {
		strarr = new Array(str.length);
		var flag = true;
		for (i = 0; i < str.length; i++) {
			strarr[i] = str.charAt(i)
			if (i == 0) {
				if (!((strarr[i] >= 'a') && (strarr[i] <= 'z'))) {
					flag = false;
				}
			} else {
				if (!((strarr[i] >= 'a') && (strarr[i] <= 'z')
						|| (strarr[i] >= '0') && (strarr[i] <= '9')
						|| (strarr[i] == '-') || (strarr[i] == '_') || (strarr[i] == '^'))) {
					flag = false;
				}
			}
		}
		if (flag) {
			return true;
		} else {
			return false;
		}
	}////////////
</script>

</head>
<body>
	<p align="center">
	<form name="check_id_form" action="<c:url value='/board/idCheck.do'/>">		
	<!-- jstl�� EL�� ����Ͽ�  ��밡���� ���̵����� ǥ�� -->
	<table border=0>
	<tr>
		<td height=35>	
			<input type="text" name="st_id" 
			     value="${param.st_id}">
			<!-- EL:request.getParameter("id")=>param.id -->
			<a href="javascript:checkSubmit()">
			ID�ߺ��˻�
			</a>
			
		</td>
	</tr>
	</table>
	<c:choose>
		<c:when test="${checkCount==0}">
	   "${st_id}"�� ��밡���մϴ�<br>
			<a href="javascript:sendId('${param.st_id}')">����ϱ�</a>
		</c:when>
		<c:otherwise>
           "${st_id}"�� �ߺ��Ǵ� ���̵��Դϴ�
    </c:otherwise>
	</c:choose>
	</form>
	</p>
</body>
</html>