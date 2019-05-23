package com.ecommerce.login.consumer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ecommerce.user.services.UserService;

import java.sql.*;
import java.time.LocalDate;

/**
 * Servlet implementation class UserLogin
 */
public class UserLogin extends HttpServlet {
	final static Logger logger = Logger.getLogger(UserLogin.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService=new UserService();
		String uname = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		String result="";
		try {
		result = userService.CheckLogin(uname,pass);
		} catch (SQLException e) {
			logger.error(e);
		}
		if(result.equals("normal")) 
		{
		HttpSession session = request.getSession();
		session.setAttribute("username", uname);
		
		response.sendRedirect("HomePage.jsp");
		return;
		}
		else if(result.equals("mail")) 
		{
		HttpSession session = request.getSession();
		session.setAttribute("username", uname);
		response.sendRedirect("changePassword.jsp");
		return;
		}
		else
		{
			request.getSession().removeAttribute("errorMessage");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
