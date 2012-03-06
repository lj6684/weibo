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
 * Servlet implementation class ListUserServlet
 */
public class ListUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		User user = (User)request.getSession().getAttribute("user");
		UserDAO userDAO = new UserDAO();
		List<User> userList = null;
		if(type.equals("target")) {
			userList = userDAO.getTagetUsers(user.getUid());
		} else if(type.equals("fans")){
			userList = userDAO.getFansUsers(user.getUid());
		}
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("list_users.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
