<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>글쓰기</title></head>
<body>
<br/>
<h3>글쓰기</h3>
<div id="content_wrap">
<form action="<c:url value='write.do' />" method="post">
	제목: <input type="text" name="title" size="20"/> <br/>
	작성자: <input type="text" name="writerName" /> <br/>
	글암호: <input type="password" name="password" /> <br/>
	글내용: <br/>
	<textarea name="content" cols="40" rows="5"></textarea>
	<br/>
	<input type="submit" value="전송" />
</form>
</div>

</body>
</html>