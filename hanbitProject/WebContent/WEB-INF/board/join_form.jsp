<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ������</title>
<style>

/*ajax*/
#message {
	font-weight: bold;
	color: red;
	font-size: 13px;
}

tr:nth-child(2n) {
	background: #E9E9E9;
}
</style>
<link rel="stylesheet"
	href="<c:url value='/jQuery/jquery-ui-1.9.0.custom.css' />"
	type="text/css" media="screen" />
<script src="<c:url value='/jQuery/jquery-1.8.2.js' />"
	type="text/javascript"></script>
<script src="<c:url value='/jQuery/jquery-ui-1.9.0.custom.js' />"
	type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$('#idInput').keyup(function() {
			var data = $('#idInput').val();
			if (validate_userid(data) && data.length >= 4 && data.length < 21) {
				data = 'st_id=' + data;

				$.ajax({
					type : "POST",
					url : "<c:url value='/board/ajaxIdCheck.do' />",
					data : data,
					success : function(result) {
						if (result == 0) { //controller���� �ߺ����� �ʴ� ��� 0�� ��ȯ�ϵ��� ����
							$('#message').text("��� ������ ���̵��Դϴ�.");
							$('#check_id').val("1"); //1�� ����
						} else {
							$('#message').text("������� ���̵��Դϴ�.");
							$('#check_id').val("0");
						}
					}

				});
			} else {
				$('#message').text("���̵� ��Ģ�� ���� �ʽ��ϴ�");

			}

		});
	});

	function validate_userid(uid) {
		var pattern = new RegExp(/^[a-z0-9_]+$/);
		return pattern.test(uid);
	}
</script>

<script>
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
	
	function inputCheck() {

		var check = document.join_form;
		
		if(check.confirm.checked == false){
			alert("����� �������ּ���.");
			check.confirm.focus();
			return false;
		}
		
		if(check.st_id.value ==""){
			alert("���̵� �Է��ϼ���.");
			check.st_id.focus();
			return false;
		}
		
		if (check.st_password.value == "") {
			alert("��й�ȣ�� �Է��ϼ���.");
			check.st_password.focus();
			return false;
		}

		if (check.confirm_password.value == "") {
			alert("��й�ȣ�� �ٽ� �� �� �Է��ϼ���.");
			check.confirm_password.focus();
			return false;
		}
		
		if (check.check_id.value == 0) {
			alert("�ߺ� ���θ� Ȯ�����ּ���.");
			check.st_id.focus();
			return false;
		}

		if (check.st_password.value != check.confirm_password.value) {
			alert("��й�ȣ�� ���� �ʽ��ϴ�.");
			check.confirm_password == "";
			check.confirm_password.focus();
			return false;
		}

		if (check.st_id.value.length<4||check.st_id.value.length>20) {
			alert("ID�� 4~20�� ���̷� �Է��ϼ���.");
			return false;
		}

		if (check.st_password.value.length<4||check.st_password.value.length>20) {
			alert("�н������� ���̴� 4~20�� �Դϴ�");
			check.st_password.focus();
			return false;
		}

		//�̸�üũ
		if (check.st_name.value == "" || check.st_name.value.length < 2) {
			alert("�̸��� 2���̻� �Է��ϼ���.");
			check.st_name.focus();
			return false;
		}

		if (check.st_tel.value == "") {
			alert("����ó�� �Է��ϼ���.");
			check.st_tel.focus();
			return false;
		}
		

		if (check.st_email.value == "") {
			alert("email�� �Է����ּ���.");
			check.st_email.focus();
			return false;
		}
		
		if (!checkChar(check.st_id.value.toLowerCase())) {
			alert("ID�� �����ҹ���\���ڸ� ����\n �������� �����ؾ� �մϴ�.");
			check.st_id.focus();
			return false;
		} 
		
		check.submit();
		return false;
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
	// str�� �ѱ��̾�߸� �Ѵ�. 
	function CheckHangul(str) {
		strarr = new Array(str.length);
		schar = new Array('/', '.', '>', '<', ',', '?', '}', '{', ' ', '\\',
				'|', '(', ')', '+', '=');
		flag = true;
		for (i = 0; i < str.length; i++) {
			for (j = 0; j < schar.length; j++) {
				if (schar[j] == str.charAt(i)) {
					flag = false;
				}
			}
			strarr[i] = str.charAt(i)
			if ((strarr[i] >= 0) && (strarr[i] <= 9)) {
				flag = false;
			} else if ((strarr[i] >= 'a') && (strarr[i] <= 'z')) {
				flag = false;
			} else if ((strarr[i] >= 'A') && (strarr[i] <= 'Z')) {
				flag = false;
			} else if ((escape(strarr[i]) > '%60')
					&& (escape(strarr[i]) < '%80')) {
				flag = false;
			}
		}
		if (flag) {
			return true;
		} else {
			return false;
		}
	}
</script>

</head>

<body>
	<div class="script_background">
	<div id="content_wrap">
	<!-- ��ȿ�� �˻� -->
	<spring:hasBindErrors name="join_command" />
	<form:errors path="join_command" />

	<!-- script���� ����� form �̸� ���� -->
	<form name="join_form" action="<c:url value='join.do' />" method="post"
		id="join_form" onsubmit="return inputCheck()">
		
		<table>
			<tr>
				<td class="font-size" colspan="2" align="center">
					<div class="box">
					<br/><br/>
						<b>ȸ�������� �ϱ⿡ �ռ� ȸ������� �о�ñ� �ٶ��ϴ�.</b>
					</div>
					<p>
						<textarea class="font-size" rows="3" cols="80">
				���ͳ� ���θ� �����θ���ȸ�� ���

				��Ģ ȸ������, Ż�� ���� �̿� ��ȯ, ȯ�� ������ȣ, �����ǹ�, å��
			</textarea>
				</td>
			</tr>
			<tr>
				<td class="font-color" width="150px" bgcolor="#6799FF" align="right">����
					�����*</td>
				<td width="650px"><input type="checkbox" name="confirm"/>[�����մϴ�]</td>
			</tr>
			<tr>
				<td width= "650px" colspan="2">*ȸ�� ������ �Է��Ͻ� �� <b>�����ϱ�</b>�� �����ֽʽÿ�.<br/> 
				*ǥ�ô� �ʼ��Է��Դϴ�.
				</td>
			</tr>
		</table>
		
		<table>
			<tr>
				<td class="font-color" bgcolor="#6799FF" align="right" width="150px">*ID:</td>
				<td><input type="text" name="st_id" id="idInput"
					value="${join_command.st_id}" /> <form:errors
						path="join_command.st_id" /> <input type="hidden" name="check_id"
					id="check_id" value="0" /> <!-- <a href="javascript:checkID()">�ߺ�Ȯ��</a> -->
					<div id="message"></div></td>
			</tr>
			<tr>
				<td class="font-color" bgcolor="#6799FF" align="right">*��й�ȣ:</td>
				<td><input type="password" name="st_password"
					value="${join_command.st_password}" /> <form:errors
						path="join_command.st_password" /></td>
			</tr>
			<tr>
				<td class="font-color" bgcolor="#6799FF" align="right">*��й�ȣ Ȯ��:</td>
				<td><input type="password" name="confirm_password" /></td>
			</tr>
			<tr>
				<td class="font-color" bgcolor="#6799FF" align="right">*�̸�:</td>
				<td><input type="text" name="st_name"
					value="${join_command.st_name}" /> <form:errors
						path="join_command.st_name" /></td>
			</tr>
			<tr>
				<td class="font-color" bgcolor="#6799FF" align="right">*����ó:</td>
				<td><input type="text" name="st_tel"
					value="${join_command.st_tel}" /> <form:errors
						path="join_command.st_tel" /></td>
			</tr>
			<tr>
				<td class="font-color" bgcolor="#6799FF" align="right">���� :</td>
				<td>
				<select name = "st_address">
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
				</select>
				<%-- <input type="text" name="st_address"
					value="${join_command.st_address}" /> <form:errors
						path="join_command.st_address" /> --%>
						</td>
			</tr>
			<tr>
				<td class="font-color" bgcolor="#6799FF" align="right">*e-mail :</td>
				<td><input type="text" name="st_email"
					value="${join_command.st_email}" /> <form:errors
						path="join_command.st_email" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="ȸ������" /></td>
				<td><input type="reset" value="�ٽ��ۼ�" /></td>
			</tr>
		</table>

	</form>
</div>
</div>
</body>
</html>