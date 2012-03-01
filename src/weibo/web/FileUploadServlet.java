package weibo.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUploadServlet
 */
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 处理文件上传
		// 判断是否为上传文件模式
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (isMultipart) {
			// 构建解析器对象
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// 提取所有请求数据
				List<FileItem> items = upload.parseRequest(request);
				for(int i = 0; i < items.size(); i++) {
					FileItem item = items.get(i);
					
					if(item.isFormField()) {
						// 普通参数
						String name = item.getFieldName();
						String value = item.getString();
						System.out.println("普通参数:" + name + "=" + value);
						
						request.setAttribute(name, value);
					} else {
						// 文件
						String name = item.getFieldName();
						String fileName = item.getName();
						String contentType = item.getContentType();
						
						System.out.println("上传文件:" + fileName);
						System.out.println("文件类型:" + contentType);
						
						File file = new File("/Users/lijian/D/JP/edu/weibo/WebContent/upload/" + fileName);
						item.write(file);
						
						request.setAttribute("imgName", fileName);
					}
				}
				
				// 跳转结果页面
				request.getRequestDispatcher("fileupload_ok.jsp").forward(request, response);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
