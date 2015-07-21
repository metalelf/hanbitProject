<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>ȸ�� ���� �����ϱ�</title>
<script>
	function edit() {

		var editCheck = document.edit_form;
		
		if(editCheck.st_address.value == "�����ϼ���"){
			alert("������ �����ϼ���.");
			editCheck.st_address.focus();
			return false;
		}

		if (editCheck.st_password.value == "") {
			alert("��й�ȣ�� �Է��ϼ���.");
			editCheck.st_password.focus();
			return false;
		}

		if (editCheck.st_password.value.length<4||editCheck.st_password.value.length>20) {
			alert("�н������� ���̴� 4~20�� �Դϴ�");
			editCheck.st_password.focus();
			return false;
		}

		//��ȭ��ȣüũ
		if (editCheck.st_tel.value == "") {
			alert("��ȭ��ȣ�� �Է��ϼ���.");
			editCheck.st_tel.focus();
			return false;
		}

		editCheck.submit();
	}/////////////////////////
</script>
</head>
<body>
<br/>
<div id="content_wrap">
<h3>��������</h3>

		<form name="edit_form" id="edit_form"
			action="<c:url value='edit.do' />" method="post"
			onsubmit="return edit()">
			<input type="hidden" name="st_id" value="${member.st_id}" />
			
			<table>
				<tr>
					<td class="font-color" bgcolor="#6799FF" align="right" width="150px">��й�ȣ:</td>
					<td><input type="password" name="st_password" size="20"
						value="${member.st_password}" /></td>
				</tr>
				<tr>
					<td class="font-color" bgcolor="#6799FF" align="right" width="150px">����ó:</td>
					<td><input type="text" name="st_tel" size="20"
						value="${member.st_tel}" /></td>
				</tr>
				<tr>
					<td class="font-color" bgcolor="#6799FF" align="right" width="150px">���� :</td>
					<td><select name="st_address">
							<option>�����ϼ���</option>
							<option>����</option>
							<option>��õ</option>
							<option>���</option>
							<option>����</option>
							<option>�泲</option>
							<option>���</option>
							<option>����</option>
							<option>�뱸</option>
							<option>�λ�</option>
							<option>���</option>
							<option>���</option>
							<option>�泲</option>
							<option>����</option>
							<option>����</option>
							<option>����</option>
							<option>����</option>
							<option>�ؿ�</option>
					</select></td>
				</tr>
				<tr>
					<td class="font-color" bgcolor="#6799FF" align="right" width="150px">e-mail:</td>
					<td><input type="text" name="st_email" size="20"
						value="${member.st_email}" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="����" /></td>
				</tr>
			</table>
		</form>
		</div>
</body>
</html>