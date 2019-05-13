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
	Connection con=null;
	PreparedStatement ps1 = null;
	ResultSet rs = null;
	  Gson converter = new Gson();
	List<String> myCustomList =new ArrayList<String>(); 
	JsonParser jp=new JsonParser();
	String uname=request.getSession().getAttribute("username").toString();
	String old_password=request.getParameter("oldpassword");
	String password=request.getParameter("password");
	String confirmpassword=request.getParameter("confirmpassword");
	String password_history=""; 
	if(password.equals(confirmpassword))
	 {
		con = MysqlCon.getConnection();
		try 
		{
			ps1=con.prepareStatement("select user_pasword_history from user where user_name=?");
			ps1.setString(1,uname);
			rs=ps1.executeQuery();
			if(rs.next())
			{
				password_history=rs.getString(1);
			}
			if(password_history!=null)
			{
				
			    Type type = new TypeToken<List<String>>(){}.getType();
			    myCustomList = converter.fromJson(password_history, type ); 
			    if(myCustomList.contains(password)) 
			    {
			    	logger.error("U used one of the existing password");
			    }
			    else {
			    JsonArray jsonArray  = new Gson().toJsonTree(myCustomList).getAsJsonArray();
                if(jsonArray.size()==3)
                {
                	myCustomList.add(0,password);
            	    myCustomList=myCustomList.subList(0,3);
                }
                else
                {
                	myCustomList.add(password);
             
                }
			    }
			}
			else
			{
	          myCustomList.add(password);
	        }
		   	password_history=new Gson().toJson(myCustomList).toString();
			ps1=con.prepareStatement("update user set user_password=?,user_pasword_history=? where user_name=?");
        	ps1.setString(1,password);
        	ps1.setString(2,password_history);
        	ps1.setString(3,uname);
            ps1.executeUpdate();
            response.sendRedirect("HomePage.jsp");
		   
		  
        }
        catch (SQLException e) {
			logger.error(e);
	    } 
		finally 
		{
		   try {
				 con.close();
				 ps1.close();
				 rs.close();
		        } 
		  catch (SQLException e) 
		   {
			logger.error(e);
		   }
		}
     }
	 else
	 {
		response.sendRedirect("changePassword.jsp?msg=missmatch_new_password");
	 }
  }
}
