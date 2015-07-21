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
	// 1. multipart/form-data ���� Ȯ��
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	if (!isMultipart) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}

	// 2. �޸𸮳� ���Ϸ� ���ε� ���� �����ϴ� FileItem�� Factory ����
	DiskFileItemFactory factory = new DiskFileItemFactory();

	// 3. ���ε� ��û�� ó���ϴ� ServletFileUpload ����
	ServletFileUpload upload = new ServletFileUpload(factory);

	// 4. ���ε� ��û �Ľ��ؼ� FileItem ��� ����
	List<FileItem> items = upload.parseRequest(request);
	AddRequest addRequest = new AddRequest();
	Iterator<FileItem> iter = items.iterator();

	while (iter.hasNext()) {
		FileItem item = iter.next();

		// 5. FileItem�� �� �Է� �׸����� ���ο� ���� �˸��� ó��
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
<title>���ε� ����</title>
</head>
<body>
	<div class="script_background">
		<div id="content_wrap">
			<%=pdsItem.getFileName()%>
			������ ���ε� �߽��ϴ�. <br /> <a href="list.jsp">��Ϻ���</a>
		</div>
	</div>
</body>
</html>