package pds.controller;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import pds.model.AddRequest;
import pds.model.PdsItem;
import pds.module.FileSaveHelper;
import pds.service.AddPdsItemService;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.setCharacterEncoding("euc-kr");
		
		//resp.setContentType("text/html; charset=euc-kr");
		//PrintWriter writer = resp.getWriter();
		//writer.println("<html><body>");
		String contentType = req.getContentType();
		
		if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			//printPartInfo(req, writer);
			PdsItem uploadedItem = saveUploadFile(req);
			req.setAttribute("uploadedItem", uploadedItem);
			RequestDispatcher dispatcher = req.getRequestDispatcher("uploaded.do");
			dispatcher.forward(req, resp);
		}
		
		else {
			//writer.println("multipart°¡ ¾Æ´Ô");
			RequestDispatcher dispatcher = req.getRequestDispatcher("invalid.do");
			dispatcher.forward(req, resp);
		}
		
		//writer.println("</body></html>");
		
	}
	
	private PdsItem saveUploadFile(HttpServletRequest req) throws IOException, ServletException {
		Part descPart = req.getPart("description");
		String description = readParameterValue(descPart);
		Part filePart = req.getPart("file");
		String fileName = getFileName(filePart);
		String realPath = FileSaveHelper.save("c:\\fileupload", filePart.getInputStream());
		
		AddRequest addRequest = new AddRequest();
		addRequest.setFileName(fileName);
		addRequest.setFileSize(filePart.getSize());
		addRequest.setDescription(description);
		addRequest.setRealPath(realPath);
		
		PdsItem pdsItem = AddPdsItemService.getInstance().add(addRequest);
		return pdsItem;
	}
	
	/*private void printPartInfo(HttpServletRequest req, PrintWriter writer) throws IOException, ServletException {
		Collection<Part> parts = req.getParts();
		
		for (Part part : parts) {
			writer.println("<br/> name = " + part.getName());
			String contentType = part.getContentType();
			writer.println("<br/> contentType = " + contentType);
			
			if (contentType != null && contentType.startsWith("application/")) {
				long size = part.getSize();
				writer.println("<br/> size = " + size);
				String fileName = getFileName(part);
				writer.println("<br/> filename = " + fileName);
				if (size > 0) {
					part.write("c:\\temp\\" + fileName);
					part.delete();
				}
			} 
			
			else {
				String value = readParameterValue(part);
				writer.println("<br/> value = " + value);
			}
			
			writer.println("<hr/>");
		}
	}*/
	
	private String getFileName(Part part) throws UnsupportedEncodingException {
		
		for (String cd : part.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		
		return null;
	}
	
	private String readParameterValue(Part part) throws IOException {
		InputStreamReader reader = new InputStreamReader(part.getInputStream(), "euc-kr");
		
		char[] data = new char[512];
		
		int len = -1;
		
		StringBuilder builder = new StringBuilder();
		
		while ((len = reader.read(data)) != -1) {
			builder.append(data, 0, len);
		}
		
		return builder.toString();
	}

}
