package com.ecommerce.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

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
		Connection con = null; 
		ResultSet rs = null;
		String uname = request.getParameter("name");
		String pass = request.getParameter("pass");
		con = MysqlCon.getConnection();
		PreparedStatement ps = null;
		int visit_count = 1;
		try {
			ps = con.prepareStatement("select user_name,user_password,user_lastlogin_time,user_visitcount from user where user_name=? and user_password=?");
			ps.setString(1, uname);
			ps.setString(2, pass);
			rs = ps.executeQuery();
            
			while (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("username", uname);
				
				Timestamp lastLogin = rs.getTimestamp(3);
				visit_count=rs.getInt(4);
				if(lastLogin!=null)
				{
					LocalDate lastLoginDate = new Date(lastLogin.getTime()).toLocalDate();
					Date date_current = new Date(System.currentTimeMillis());		
					logger.info("date->{}"+lastLoginDate.isEqual(date_current.toLocalDate()));
					if(lastLoginDate.isEqual(date_current.toLocalDate()))
					{
						visit_count++;
					}
					else
					{
						visit_count=1;
					}
				}
				
				java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
				ps = con.prepareStatement("update user set user_lastlogin_time=?, user_visitcount=? where user_name=?");
				ps.setTimestamp(1, date);
				ps.setInt(2,visit_count);
				ps.setString(3, uname);
				ps.executeUpdate();
				response.sendRedirect("HomePage.jsp");
				return;
			}
			request.getSession().removeAttribute("errorMessage");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		} catch (SQLException e) {
			logger.error(e);
		}
		finally
		{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				
				logger.error(e);
			}
		}

	}
}
