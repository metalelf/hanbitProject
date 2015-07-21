<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>글쓰기</title>

<script>
	function cl_check() {

		var clInfo = document.cl_form;

		if (clInfo.cl_name.value == "") {
			alert("강의명을 입력하세요");
			clInfo.cl_name.focus();
			return false;
		}

		if (clInfo.cl_start_date.value == "") {
			alert("강의 시작일을 입력하세요");
			clInfo.cl_start_date.focus();
			return false;
		}

		if (clInfo.cl_end_date.value == "") {
			alert("강의 종료일을 입력하세요");
			clInfo.cl_end_date.focus();
			return false;
		}

		if (clInfo.cl_price.value == "") {
			alert("수강료를 입력하세요");
			clInfo.cl_price.focus();
			return false;
		}
		
		if(isNaN(clInfo.cl_price.value)){
			alert("수강료는 숫자만 입력하세요");
			clInfo.cl_price.value="";
			clInfo.cl_price.focus();
			return false;
		}
			

		if (clInfo.cl_time.value == "") {
			alert("강의 시간을 입력하세요");
			clInfo.cl_time.focus();
			return false;
		}

		if (clInfo.cl_max.value == "") {
			alert("수강 인원을 입력하세요");
			clInfo.cl_max.focus();
			return false;
		}
		
		if(isNaN(clInfo.cl_max.value)){
			alert("수강 인원은 숫자만 입력하세요");
			clInfo.cl_max.value="";
			clInfo.cl_max.focus();
			return false;
		}

		if (clInfo.cl_content.value == "") {
			alert("강의 정보를 입력하세요");
			clInfo.cl_content.focus();
			return false;
		}

		clInfo.submit();

	}
</script>

</head>
<body>
	<h3>강의정보 수정</h3>
	<div id="content_wrap">
	<form action="<c:url value='cl_update.do' />" method="post" id="cl_form" onsubmit="return cl_check()">
		<input type="hidden" name="cl_num" value="${article.cl_num}" />
		강의명: <input type="text" name="cl_name" size="20" value="${article.cl_name}" /> <br/>
		강사명: <select name="te_num">
				<c:forEach var="teacher" items="${teacher}">
					<c:if test="${article.te_num == teacher.te_num}">
						<option value="${teacher.te_num }" selected="selected">${teacher.te_name }</option>
					</c:if>
					<c:if test="${article.te_num != teacher.te_num}">
						<option value="${teacher.te_num }">${teacher.te_name }</option>
					</c:if>
				</c:forEach>
			  </select> <br/>
		강의 시작일: <input type="text" name="cl_start_date" size="20" value="${article.cl_start_date}" /> <br/>
		강의 종료일: <input type="text" name="cl_end_date" size="20" value="${article.cl_end_date}" /> <br/>
		강의료: <input type="text" name="cl_price" size="20" value="${article.cl_price}" /> <br/>
		강의시간: <input type="text" name="cl_time" size="20" value="${article.cl_time}" /> <br/>
		수강인원: <input type="text" name="cl_max" size="20" value="${article.cl_max}" /> <br />
		강의정보: <br/>
		<textarea name="cl_content" cols="40" rows="5">${article.cl_content}</textarea>
		<br/>
		<input type="submit" value="강의수정" />
	</form>
	</div>
</body>
</html>