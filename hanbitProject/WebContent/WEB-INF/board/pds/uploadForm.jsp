<%@ page contentType="text/html; charset=euc-kr"%>
<html>
<head>
<title>���� ���</title>
</head>
<body>
	<div class="script_background">
		<div id="content_wrap">
			<form action="/Project/board/fileUpload" method="post"
				enctype="multipart/form-data">
				����: <input type="file" name="file" /> <br /> ����: <input type="text"
					name="description" /> <br /> <input type="submit" value="���ε�" />
			</form>
		</div>
	</div>
</body>
</html>