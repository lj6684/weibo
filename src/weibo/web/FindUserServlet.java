package weibo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weibo.dao.UserDAO;
import weibo.dao.model.User;

/**
 * Servlet implementation class FindUserServlet
 */
public class FindUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUserServlet() {
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
		User user = (User)request.getSession().getAttribute("user");
		String nickName = request.getParameter("nickName");
		UserDAO userDAO = new UserDAO();
		List<User> users = userDAO.findUserByNickName(user.getUid(), nickName);
		
		request.setAttribute("users", users);
		request.getRequestDispatcher("find.jsp").forward(request, response);
	}

}
