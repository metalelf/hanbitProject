<%@ page contentType="text/html; charset=euc-kr" %>
<html>
<head><title>수정하기</title></head>
<body>
<br/>
수정하실 내용을 입력하세요.<br/>
<div id = "content_wrap">
<form action="update.do" method="post">
	<input type="hidden" name="articleId" value="${article.id}" />
	<input type="hidden" name="page" value="${param.page}" />
	제목: <input type="text" name="title" size="20" value="${article.title}" /> <br/>
	글암호: <input type="password" name="password" /> <br/>
	글내용: <br/>
	<textarea name="content" cols="40" rows="5">${article.content}</textarea>
	<br/>
	<input type="submit" value="수정" />
</form>
</div>
</body>