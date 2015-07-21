<%@ page contentType="text/html; charset=euc-kr" %>
<html>
<head><title>파일 등록</title></head>
<body>
<div class="script_background">
<div id="content_wrap">
[${uploadedItem.fileName}] 파일을 업로드 했습니다.
<br/>
<a href="<%= request.getContextPath() %>/list.jsp">[목록보기]</a>
</div>
</div>
</body>