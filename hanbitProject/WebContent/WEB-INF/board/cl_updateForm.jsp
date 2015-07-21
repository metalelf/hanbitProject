<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>�۾���</title>

<script>
	function cl_check() {

		var clInfo = document.cl_form;

		if (clInfo.cl_name.value == "") {
			alert("���Ǹ��� �Է��ϼ���");
			clInfo.cl_name.focus();
			return false;
		}

		if (clInfo.cl_start_date.value == "") {
			alert("���� �������� �Է��ϼ���");
			clInfo.cl_start_date.focus();
			return false;
		}

		if (clInfo.cl_end_date.value == "") {
			alert("���� �������� �Է��ϼ���");
			clInfo.cl_end_date.focus();
			return false;
		}

		if (clInfo.cl_price.value == "") {
			alert("�����Ḧ �Է��ϼ���");
			clInfo.cl_price.focus();
			return false;
		}
		
		if(isNaN(clInfo.cl_price.value)){
			alert("������� ���ڸ� �Է��ϼ���");
			clInfo.cl_price.value="";
			clInfo.cl_price.focus();
			return false;
		}
			

		if (clInfo.cl_time.value == "") {
			alert("���� �ð��� �Է��ϼ���");
			clInfo.cl_time.focus();
			return false;
		}

		if (clInfo.cl_max.value == "") {
			alert("���� �ο��� �Է��ϼ���");
			clInfo.cl_max.focus();
			return false;
		}
		
		if(isNaN(clInfo.cl_max.value)){
			alert("���� �ο��� ���ڸ� �Է��ϼ���");
			clInfo.cl_max.value="";
			clInfo.cl_max.focus();
			return false;
		}

		if (clInfo.cl_content.value == "") {
			alert("���� ������ �Է��ϼ���");
			clInfo.cl_content.focus();
			return false;
		}

		clInfo.submit();

	}
</script>

</head>
<body>
	<h3>�������� ����</h3>
	<div id="content_wrap">
	<form action="<c:url value='cl_update.do' />" method="post" id="cl_form" onsubmit="return cl_check()">
		<input type="hidden" name="cl_num" value="${article.cl_num}" />
		���Ǹ�: <input type="text" name="cl_name" size="20" value="${article.cl_name}" /> <br/>
		�����: <select name="te_num">
				<c:forEach var="teacher" items="${teacher}">
					<c:if test="${article.te_num == teacher.te_num}">
						<option value="${teacher.te_num }" selected="selected">${teacher.te_name }</option>
					</c:if>
					<c:if test="${article.te_num != teacher.te_num}">
						<option value="${teacher.te_num }">${teacher.te_name }</option>
					</c:if>
				</c:forEach>
			  </select> <br/>
		���� ������: <input type="text" name="cl_start_date" size="20" value="${article.cl_start_date}" /> <br/>
		���� ������: <input type="text" name="cl_end_date" size="20" value="${article.cl_end_date}" /> <br/>
		���Ƿ�: <input type="text" name="cl_price" size="20" value="${article.cl_price}" /> <br/>
		���ǽð�: <input type="text" name="cl_time" size="20" value="${article.cl_time}" /> <br/>
		�����ο�: <input type="text" name="cl_max" size="20" value="${article.cl_max}" /> <br />
		��������: <br/>
		<textarea name="cl_content" cols="40" rows="5">${article.cl_content}</textarea>
		<br/>
		<input type="submit" value="���Ǽ���" />
	</form>
	</div>
</body>
</html>