<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원가입</title>
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
						if (result == 0) { //controller에서 중복되지 않는 경우 0을 반환하도록 설정
							$('#message').text("사용 가능한 아이디입니다.");
							$('#check_id').val("1"); //1로 변경
						} else {
							$('#message').text("사용중인 아이디입니다.");
							$('#check_id').val("0");
						}
					}

				});
			} else {
				$('#message').text("아이디 규칙에 맞지 않습니다");

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
			// 공백이 없으면 종료합니다.
			if (index == -1)
				break;
			// 문자열 길이를 구합니다.
			len = str.length;
			// 공백을 잘라냅니다.
			str = str.substring(0, index) + str.substring((index + 1), len);
		}

		return str;
	}
	
	function inputCheck() {

		var check = document.join_form;
		
		if(check.confirm.checked == false){
			alert("약관에 동의해주세요.");
			check.confirm.focus();
			return false;
		}
		
		if(check.st_id.value ==""){
			alert("아이디를 입력하세요.");
			check.st_id.focus();
			return false;
		}
		
		if (check.st_password.value == "") {
			alert("비밀번호를 입력하세요.");
			check.st_password.focus();
			return false;
		}

		if (check.confirm_password.value == "") {
			alert("비밀번호를 다시 한 번 입력하세요.");
			check.confirm_password.focus();
			return false;
		}
		
		if (check.check_id.value == 0) {
			alert("중복 여부를 확인해주세요.");
			check.st_id.focus();
			return false;
		}

		if (check.st_password.value != check.confirm_password.value) {
			alert("비밀번호가 맞지 않습니다.");
			check.confirm_password == "";
			check.confirm_password.focus();
			return false;
		}

		if (check.st_id.value.length<4||check.st_id.value.length>20) {
			alert("ID는 4~20자 사이로 입력하세요.");
			return false;
		}

		if (check.st_password.value.length<4||check.st_password.value.length>20) {
			alert("패스워드의 길이는 4~20자 입니다");
			check.st_password.focus();
			return false;
		}

		//이름체크
		if (check.st_name.value == "" || check.st_name.value.length < 2) {
			alert("이름은 2자이상 입력하세요.");
			check.st_name.focus();
			return false;
		}

		if (check.st_tel.value == "") {
			alert("연락처를 입력하세요.");
			check.st_tel.focus();
			return false;
		}
		

		if (check.st_email.value == "") {
			alert("email을 입력해주세요.");
			check.st_email.focus();
			return false;
		}
		
		if (!checkChar(check.st_id.value.toLowerCase())) {
			alert("ID는 영문소문자\숫자만 가능\n 영문으로 시작해야 합니다.");
			check.st_id.focus();
			return false;
		} 
		
		check.submit();
		return false;
	}

	// str은 모두 소문자여야하고 첫글자는 영문이어야 한다. 
	// 영문과 0~9, -, _, ^는 허용한다. 
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
	// str은 한글이어야만 한다. 
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
	<!-- 유효성 검사 -->
	<spring:hasBindErrors name="join_command" />
	<form:errors path="join_command" />

	<!-- script에서 사용할 form 이름 지정 -->
	<form name="join_form" action="<c:url value='join.do' />" method="post"
		id="join_form" onsubmit="return inputCheck()">
		
		<table>
			<tr>
				<td class="font-size" colspan="2" align="center">
					<div class="box">
					<br/><br/>
						<b>회원가입을 하기에 앞서 회원약관을 읽어보시기 바랍니다.</b>
					</div>
					<p>
						<textarea class="font-size" rows="3" cols="80">
				인터넷 쇼핑몰 『쇼핑몰』회원 약관

				총칙 회원가입, 탈퇴 서비스 이용 교환, 환불 정보보호, 보안의무, 책임
			</textarea>
				</td>
			</tr>
			<tr>
				<td class="font-color" width="150px" bgcolor="#6799FF" align="right">위의
					약관에*</td>
				<td width="650px"><input type="checkbox" name="confirm"/>[동의합니다]</td>
			</tr>
			<tr>
				<td width= "650px" colspan="2">*회원 정보를 입력하신 후 <b>가입하기</b>를 눌러주십시오.<br/> 
				*표시는 필수입력입니다.
				</td>
			</tr>
		</table>
		
		<table>
			<tr>
				<td class="font-color" bgcolor="#6799FF" align="right" width="150px">*ID:</td>
				<td><input type="text" name="st_id" id="idInput"
					value="${join_command.st_id}" /> <form:errors
						path="join_command.st_id" /> <input type="hidden" name="check_id"
					id="check_id" value="0" /> <!-- <a href="javascript:checkID()">중복확인</a> -->
					<div id="message"></div></td>
			</tr>
			<tr>
				<td class="font-color" bgcolor="#6799FF" align="right">*비밀번호:</td>
				<td><input type="password" name="st_password"
					value="${join_command.st_password}" /> <form:errors
						path="join_command.st_password" /></td>
			</tr>
			<tr>
				<td class="font-color" bgcolor="#6799FF" align="right">*비밀번호 확인:</td>
				<td><input type="password" name="confirm_password" /></td>
			</tr>
			<tr>
				<td class="font-color" bgcolor="#6799FF" align="right">*이름:</td>
				<td><input type="text" name="st_name"
					value="${join_command.st_name}" /> <form:errors
						path="join_command.st_name" /></td>
			</tr>
			<tr>
				<td class="font-color" bgcolor="#6799FF" align="right">*연락처:</td>
				<td><input type="text" name="st_tel"
					value="${join_command.st_tel}" /> <form:errors
						path="join_command.st_tel" /></td>
			</tr>
			<tr>
				<td class="font-color" bgcolor="#6799FF" align="right">지역 :</td>
				<td>
				<select name = "st_address">
					<option>서울</option>
					<option>인천</option>
					<option>경기</option>
					<option>대전</option>
					<option>충남</option>
					<option>충북</option>
					<option>강원</option>
					<option>대구</option>
					<option>부산</option>
					<option>울산</option>
					<option>경북</option>
					<option>경남</option>
					<option>광주</option>
					<option>전남</option>
					<option>전북</option>
					<option>제주</option>
					<option>해외</option>
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
				<td><input type="submit" value="회원가입" /></td>
				<td><input type="reset" value="다시작성" /></td>
			</tr>
		</table>

	</form>
</div>
</div>
</body>
</html>