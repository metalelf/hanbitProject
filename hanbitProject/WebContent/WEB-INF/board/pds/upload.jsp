<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="pds.service.AddPdsItemService"%>
<%@ page import="pds.module.FileSaveHelper"%>
<%@ page import="pds.model.AddRequest"%>
<%@ page import="pds.model.PdsItem"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="org.apache.commons.fileupload.FileItem"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page
	import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>

<%
	// 1. multipart/form-data 여부 확인
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	if (!isMultipart) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}

	// 2. 메모리나 파일로 업로드 파일 보관하는 FileItem의 Factory 설정
	DiskFileItemFactory factory = new DiskFileItemFactory();

	// 3. 업로드 요청을 처리하는 ServletFileUpload 생성
	ServletFileUpload upload = new ServletFileUpload(factory);

	// 4. 업로드 요청 파싱해서 FileItem 목록 구함
	List<FileItem> items = upload.parseRequest(request);
	AddRequest addRequest = new AddRequest();
	Iterator<FileItem> iter = items.iterator();

	while (iter.hasNext()) {
		FileItem item = iter.next();

		// 5. FileItem이 폼 입력 항목인지 여부에 따라 알맞은 처리
		if (item.isFormField()) {
			String name = item.getFieldName();
			if (name.equals("description")) {
				String value = item.getString();
				addRequest.setDescription(value);
			}

		}

		else {
			String name = item.getFieldName();

			if (name.equals("file")) {
				String realPath = FileSaveHelper.save("c:\\fileupload",
						item.getInputStream());
				addRequest.setFileName(item.getName());
				addRequest.setFileSize(item.getSize());
				addRequest.setRealPath(realPath);
			}
		}
	}
	PdsItem pdsItem = AddPdsItemService.getInstance().add(addRequest);
%>
<html>
<head>
<title>업로드 성공</title>
</head>
<body>
	<div class="script_background">
		<div id="content_wrap">
			<%=pdsItem.getFileName()%>
			파일을 업로드 했습니다. <br /> <a href="list.jsp">목록보기</a>
		</div>
	</div>
</body>
</html>