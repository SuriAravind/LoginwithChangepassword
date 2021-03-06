package com.ecommerce.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ecommerce.user.dao.UsersDao;

import Services.UserService;

/**
 * Servlet implementation class forgotpassword
 */
@WebServlet("/forgotpassword")
public class forgotpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forgotpassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		SendForgotEmail sendForgotEmail=new SendForgotEmail();
		String randomString=RandomString.getAlphaNumericString(6);
		UserService userService=new UserService();
		String userid=userService.getUserEmailId(randomString, email);
		sendForgotEmail.send("suriyanarayanan15061998@gmail.com", "narayanan15", userid,"http://localhost:8080/ecommerce/ForgotPasswordbyLink?id="+randomString);
		response.sendRedirect("index.jsp");
	}
}
