package com.ecommerce.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import Services.UserService;

/**
 * Servlet implementation class changePassword
 */
@WebServlet("/changePassword")
public class changePassword extends HttpServlet {
	final static Logger logger = Logger.getLogger(UserLogin.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public changePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
		String username = request.getSession().getAttribute("username").toString();
		String old_password = request.getParameter("oldpassword");
		String password = request.getParameter("password");
		String confirmpassword = request.getParameter("confirmpassword");
		UserService userService = new UserService();
		int i = 0;
		i = userService.isUserPassword(username, old_password);
		if (i == 1) {
			if (password.equals(confirmpassword)) {
				userService.updatePasswordhistory(username, password);
				response.sendRedirect("HomePage.jsp");
			} else {
				response.sendRedirect("changePassword.jsp?msg=missmatch_new_password");
			}
		}
		if (i == 2) {
			if (password.equals(confirmpassword)) {
				userService.updatePasswordhistory(username, password);
				response.sendRedirect("index.jsp");
			} else {
				response.sendRedirect("changePassword.jsp?msg=missmatch_new_password");
			}

		} else {
			response.sendRedirect("changePassword.jsp?msg=missmatch_user_password");
		}
	}
}
