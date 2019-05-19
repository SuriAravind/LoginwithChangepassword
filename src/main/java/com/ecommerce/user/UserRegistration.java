package com.ecommerce.user;


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
	UsersDao userDao=new UsersDao();

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String password=request.getParameter("password");
		String Conformpassword=request.getParameter("conformpassword");
		if(password.equals(Conformpassword))
		{
		Map<String,String> parameters=new LinkedHashMap();
        parameters.put("username",request.getParameter("username"));
		parameters.put( "password",request.getParameter("password"));
		parameters.put( "email",request.getParameter("email"));
		parameters.put( "number",request.getParameter("number"));
		parameters.put( "age",request.getParameter("age"));
		int userId=userDao.createUser(parameters);
		response.getWriter().write(userId);
		response.sendRedirect("index.jsp");
		}
		else
		{
			request.getSession().removeAttribute("errorMessage");
	        request.getRequestDispatcher("/UserRegistration.jsp").forward(request, response);
			return;
		}
		
	}
}
