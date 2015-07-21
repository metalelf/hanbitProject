<%@ page contentType="text/html; charset=euc-kr" %>
<html>
<head><title>답변쓰기</title></head>
<body>
<br/>
<h3>답변쓰기</h3>
<div id="content_wrap">
<form action="reply.do" method="post">
	<input type="hidden" name="parentArticleId" value="${parentId}" />
	<input type="hidden" name="page" value="${page}" />
	제목: <input type="text" name="title" size="20" value="re: " /> <br/>
	작성자: <input type="text" name="writerName" /> <br/>
	글암호: <input type="password" name="password" /> <br/>
	글내용: <br/>
	<textarea name="content" cols="40" rows="5"></textarea>
	<br/>
	<input type="submit" value="전송" />
</form>
</div>

</body>