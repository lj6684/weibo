package weibo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weibo.dao.UserDAO;
import weibo.dao.model.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findUser(userName);
		if(user == null) {
			// 用户不存在
			request.setAttribute("errorMsg", "用户不存在");
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		} else {
			if(password.equals(user.getPassword())) {
				// 口令正确
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("login_res.jsp").forward(request, response);
			} else {
				// 口令错误
				request.setAttribute("errorMsg", "用户口令错误");
				request.getRequestDispatcher("welcome.jsp").forward(request, response);
			}
		}
	}

}
