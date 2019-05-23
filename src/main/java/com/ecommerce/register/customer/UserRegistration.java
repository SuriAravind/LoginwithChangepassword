package com.ecommerce.register.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ecommerce.user.dao.UsersDao;
import com.ecommerce.user.services.UserService;

/**
 * Servlet implementation class userRegistration
 */
@WebServlet("/userResgistration")
public class UserRegistration extends HttpServlet {
	final static Logger logger = Logger.getLogger(UserRegistration.class);
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public UserRegistration() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = new UserService();
		String password = request.getParameter("password");
		String Conformpassword = request.getParameter("conformpassword");
		if (password.equals(Conformpassword)) {
			Map<String, String> parameters = new LinkedHashMap();
			parameters.put("username", request.getParameter("username"));
			parameters.put("password", request.getParameter("password"));
			parameters.put("email", request.getParameter("email"));
			parameters.put("number", request.getParameter("number"));
			parameters.put("age", request.getParameter("age"));
			int result = userService.createUser(parameters);
			if(result==0) {
			response.sendRedirect("index.jsp");
			}
			else 
			{
				response.sendRedirect("userRegister.jsp?msg=User_or_EmailId_AlreadyExists");
			}
		} 
		else {
			request.getSession().removeAttribute("errorMessage");
			request.getRequestDispatcher("/UserRegistration.jsp").forward(request, response);
			return;
		}

	}
}
