<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>�� �б�</title>

<script>
	function LoginCheck() {

		alert("�α��� �� �̿� �����մϴ�.");
		self.close();
		return false;
	}

	function checkClass() {
		alert("�̹� ��û�� �����Դϴ�.");
		self.close();
		return false;
	}
</script>

</head>
<body>
	<br />
	<div id="content_wrap">
		<h3>���� ����</h3>

		<table border="1">
			<tr>
				<td>���Ǹ�</td>
				<td>${article.cl_name}</td>
			</tr>
			<tr>
				<td>�����</td>
				<td>${article.te_name}</td>
			</tr>
			<tr>
				<td>���� ������</td>
				<td>${article.cl_start_date}</td>
			</tr>
			<tr>
				<td>���� ������</td>
				<td>${article.cl_end_date}</td>
			</tr>
			<tr>
				<td>���Ƿ�</td>
				<td>${article.cl_price}</td>
			</tr>
			<tr>
				<td>���ǽð�</td>
				<td>${article.cl_time}</td>
			</tr>
			<tr>
				<td>�����ο�</td>
				<td>${article.cl_current}/${article.cl_max}</td>
			</tr>
			<tr>
				<td>����</td>
				<td><pre>
						<c:out value="${article.cl_content}" />
					</pre></td>
			</tr>

			<c:if test="${sessionId == null}">
				<tr>
					<td colspan="2">
						<form action="javascript:LoginCheck()">
							<input type="submit" value="���ǰ���" />
						</form>
					</td>
				</tr>
			</c:if>

			<c:if test="${sessionId != null}">
				<%-- <c:if test="${num == 0}"> --%>
				<c:if test="${cl!=0}">
					<tr>
						<td colspan="2">
							<form action="javascript:checkClass()">
								<input type="submit" value="���ǰ���" />
							</form>
						</td>
					</tr>
				</c:if>

				<c:if test="${cl == 0}">

					<c:if test="${article.cl_current < article.cl_max}">
						<tr>
							<td colspan="2">
								<form action="<c:url value='cl_buy.do' />" method="post">
									<input type="hidden" name="cl_num" value="${article.cl_num}" />
									<input type="hidden" name="cl_price"
										value="${article.cl_price}" /> <input type="hidden"
										name="cl_name" value="${article.cl_name}" /> <input
										type="submit" value="���ǰ���" />
								</form>
							</td>
						</tr>
					</c:if>

					<c:if test="${article.cl_current >= article.cl_max}">
						<tr>
							<td colspan="2">����</td>
						</tr>
					</c:if>

				</c:if>
				
				<%-- </c:if> --%>
			</c:if>
			<tr>
				<td colspan="2"><a href="cl_list.do?page=${param.page}">��Ϻ���</a>
					<%-- <a href="cl_update.do?cl_num=${article.cl_num}&page=${param.page}">�����ϱ�</a>
					<a href="cl_delete.do?cl_num=${article.cl_num}">�����ϱ�</a></td> --%>
			</tr>
		</table>
	</div>
</body>
</html>