<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>�Խñ� �ۼ�</title></head>
<body>
<br/>
�Խñ��� ����߽��ϴ�.
<br/>
<a href="<c:url value='/board/list.do?page=1'/>">��Ϻ���</a>
<a href="<c:url value='/board/read.do?articleId=${articleId}&page=1'/>">�Խñ� �б�</a>

</body>
</html>