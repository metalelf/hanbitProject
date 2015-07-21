<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>아이디중복체크</title>

<script>
	//////////////////아이디 중복검사 후 해당 아이디를 가입폼 ID입력창으로 전송 + 비밀번호 입력 창으로 커서 이동
	function sendId(st_id){
 	 	alert(st_id);
 	 	//팝업창의 내용을 호출한 화면(member.jsp)으로 보냄
 	 	
 	 	opener.join_form.st_id.value=st_id;
 	 	opener.join_form.st_password.focus();
 	 	//해당 팝업창을 닫는다!(자기자신)
 	 	self.close();
 	}//sendId(id)

	////////////////////정보 입력 후 회원가입 버튼을 누르면 동작하는 script
	function checkSubmit() {

		//아이디값을 체크 4~12,영문 숫자 _ - ^ 숫자로 시작불가
		var id = document.check_id_form.st_id.value;
		id = Space_All(id);
		if (id == null || id.length<4 ||id.length>20) {
			alert("아이디는 4~20자 사이의 값을 입력!!");
			return;
		}
		if (CheckChar(id) == false) {
			alert("아이디는 영문,숫자를 섞어 지정가능하나 숫자로 시작은 불가");
			return;
		}
		/////////////////입력한 정보가 제약 조건들을 충족할 경우 submit
		check_id_form.submit();

	}

	/////////////////////////////공백검사
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
</script>

</head>
<body>
	<p align="center">
	<form name="check_id_form" action="<c:url value='/board/idCheck.do'/>">		
	<!-- jstl과 EL을 사용하여  사용가능한 아이디인지 표시 -->
	<table border=0>
	<tr>
		<td height=35>	
			<input type="text" name="st_id" 
			     value="${param.st_id}">
			<!-- EL:request.getParameter("id")=>param.id -->
			<a href="javascript:checkSubmit()">
			ID중복검사
			</a>
			
		</td>
	</tr>
	</table>
	<c:choose>
		<c:when test="${checkCount==0}">
	   "${st_id}"는 사용가능합니다<br>
			<a href="javascript:sendId('${param.st_id}')">사용하기</a>
		</c:when>
		<c:otherwise>
           "${st_id}"는 중복되는 아이디입니다
    </c:otherwise>
	</c:choose>
	</form>
	</p>
</body>
</html>