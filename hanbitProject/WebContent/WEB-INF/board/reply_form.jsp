<%@ page contentType="text/html; charset=euc-kr" %>
<html>
<head><title>�亯����</title></head>
<body>
<br/>
<h3>�亯����</h3>
<div id="content_wrap">
<form action="reply.do" method="post">
	<input type="hidden" name="parentArticleId" value="${parentId}" />
	<input type="hidden" name="page" value="${page}" />
	����: <input type="text" name="title" size="20" value="re: " /> <br/>
	�ۼ���: <input type="text" name="writerName" /> <br/>
	�۾�ȣ: <input type="password" name="password" /> <br/>
	�۳���: <br/>
	<textarea name="content" cols="40" rows="5"></textarea>
	<br/>
	<input type="submit" value="����" />
</form>
</div>

</body>