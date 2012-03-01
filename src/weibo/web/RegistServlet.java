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

import weibo.dao.UserDAO;
import weibo.dao.model.User;

/**
 * Servlet implementation class RegistServlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = null;
		String nickName = null;
		String password = null;
		String local = null;
		String headImg = null;
		
		// 上传图片保存路径
		String realPath = getServletContext().getRealPath("img/head");
		//System.out.println(realPath);
		
		// 判断是否为上传文件模式
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart) {
			//为该请求创建一个 DiskFileItemFactory 对象,通过它来解析请求。执行解析后,所有的表单项目都保存在一个 List 中。
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// 提取所有请求数据
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					String filedName = item.getFieldName();
					// 检查当前项目是普通表单项目还是上传文件
					if (item.isFormField()) {
						// 普通表单内容
						if(filedName.equals("userName")) {
							name = item.getString();
						} else if(filedName.equals("nickName")) {
							nickName = item.getString();
						} else if(filedName.equals("password")) {
							password = item.getString();
						} else if(filedName.equals("local")) {
							local = item.getString();
						}
					} else {
						// 文件内容
						String fileName = item.getName();
						String[] strs = fileName.split("\\.");
						String endix = strs[strs.length - 1];
					    long uuid = UUID.randomUUID().getLeastSignificantBits();
					    String saveFile = uuid + "." + endix;
					    File uploadedFile = new File(realPath + "/" + saveFile);
					    item.write(uploadedFile);
					    headImg = "img/head/" + saveFile;
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				request.setAttribute("errorMsg", "处理上传文件失败");
				request.getRequestDispatcher("regist.jsp").forward(request, response);
				return;
			}
		}
		
		UserDAO userDAO = new UserDAO();
		// 检查用户信息有效性
		User user = userDAO.findUser(name);
		if(user != null) {
			request.setAttribute("errorMsg", "该用户名已存在，请更换其他名字");
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return;
		}
		
		// 保存用户，返回注册结果
		user = new User();
		user.setName(name);
		user.setNickName(nickName);
		user.setPassword(password);
		user.setLocal(local);
		user.setHeadImg(headImg);
		
		int i = userDAO.saveUser(user);
		if(i == 1) {
			// 保存成功
			user = userDAO.findUser(name);
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("regist_res.jsp").forward(request, response);
		} else {
			// 保存失败
			request.setAttribute("errorMsg", "保存用户信息失败");
			request.getSession().setAttribute("user", null);
			request.getRequestDispatcher("regist.jsp").forward(request, response);
		}
	}

}
