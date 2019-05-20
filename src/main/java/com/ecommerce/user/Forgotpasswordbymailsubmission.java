package com.ecommerce.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Services.UserService;

@WebServlet("/Forgotpasswordbymailsubmission")
public class Forgotpasswordbymailsubmission extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Forgotpasswordbymailsubmission() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String password=request.getParameter("password");
    	String confirmpassword=request.getParameter("confirmpassword");
	   UserService userService=new UserService();
	   HttpSession session = request.getSession();
		String uname=(String) session.getAttribute("username");
	   if(password.equals(confirmpassword)) {
		   userService.updatePasswordbyemail(uname,password);
		   response.sendRedirect("index.jsp");
		}
	   else
	   {
			response.sendRedirect("ForgotPasswordbyLink.jsp?msg=missmatch__password");
		}
	   
    }

}
