package weibo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weibo.dao.MessageDAO;
import weibo.dao.model.Message;
import weibo.dao.model.User;

/**
 * Servlet implementation class PubMessageServlet
 */
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		
		// 获取最新微博列表
		MessageDAO messageDAO = new MessageDAO();
		List<Message> messageList = messageDAO.getMessages(user.getUid());
			
		request.setAttribute("messages", messageList);
		request.getRequestDispatcher("messages.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String content = request.getParameter("content");
		User user = (User)request.getSession().getAttribute("user");
		
		MessageDAO messageDAO = new MessageDAO();

		Message message = new Message();
		message.setUid(user.getUid());
		message.setContent(content);
		
		// 发布微博
		int res = messageDAO.saveMessage(message);
		
		if(res == 1) {
			// 保存微博成功
			request.setAttribute("info", "微博发布成功");
		} else {
			// 保存微博失败
			request.setAttribute("errorMsg", "微博发布失败");
		}
		
		
		// 获取最新微博列表
		List<Message> messageList = messageDAO.getMessages(user.getUid());
		
		request.setAttribute("messages", messageList);
		request.getRequestDispatcher("messages.jsp").forward(request, response);
	}

}
