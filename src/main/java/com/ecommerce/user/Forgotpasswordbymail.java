package com.ecommerce.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Services.UserService;


public class Forgotpasswordbymail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Forgotpasswordbymail() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService=new UserService();
		String randomId=request.getParameter("id");
		String username=userService.getUserName(randomId);
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		response.sendRedirect("ForgotPasswordbyLink.jsp");
		return;
	}
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
