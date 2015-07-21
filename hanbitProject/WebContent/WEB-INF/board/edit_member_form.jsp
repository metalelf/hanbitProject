<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<title>회원 정보 수정하기</title>
<script>
	function edit() {

		var editCheck = document.edit_form;
		
		if(editCheck.st_address.value == "선택하세요"){
			alert("지역을 선택하세요.");
			editCheck.st_address.focus();
			return false;
		}

		if (editCheck.st_password.value == "") {
			alert("비밀번호를 입력하세요.");
			editCheck.st_password.focus();
			return false;
		}

		if (editCheck.st_password.value.length<4||editCheck.st_password.value.length>20) {
			alert("패스워드의 길이는 4~20자 입니다");
			editCheck.st_password.focus();
			return false;
		}

		//전화번호체크
		if (editCheck.st_tel.value == "") {
			alert("전화번호를 입력하세요.");
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
<h3>정보수정</h3>

		<form name="edit_form" id="edit_form"
			action="<c:url value='edit.do' />" method="post"
			onsubmit="return edit()">
			<input type="hidden" name="st_id" value="${member.st_id}" />
			
			<table>
				<tr>
					<td class="font-color" bgcolor="#6799FF" align="right" width="150px">비밀번호:</td>
					<td><input type="password" name="st_password" size="20"
						value="${member.st_password}" /></td>
				</tr>
				<tr>
					<td class="font-color" bgcolor="#6799FF" align="right" width="150px">연락처:</td>
					<td><input type="text" name="st_tel" size="20"
						value="${member.st_tel}" /></td>
				</tr>
				<tr>
					<td class="font-color" bgcolor="#6799FF" align="right" width="150px">지역 :</td>
					<td><select name="st_address">
							<option>선택하세요</option>
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
					</select></td>
				</tr>
				<tr>
					<td class="font-color" bgcolor="#6799FF" align="right" width="150px">e-mail:</td>
					<td><input type="text" name="st_email" size="20"
						value="${member.st_email}" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="수정" /></td>
				</tr>
			</table>
		</form>
		</div>
</body>
</html>