package com.ecommerce.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.log4j.Logger;

import com.ecommerce.user.MysqlCon;
import com.ecommerce.util.QueryPropertyUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.protobuf.Timestamp;

public class UsersDao {

	// QueryPropertyUtils queryPropertyUtils = new QueryPropertyUtils();
	final static Logger logger = Logger.getLogger(QueryPropertyUtils.class);

	public int createUser(Map<String, String> parameters) {
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		Connection con = MysqlCon.getConnection();
		PreparedStatement stmt = null;
		int i = 0;
		try {
			stmt = con.prepareStatement(
					"insert into user(user_name,user_password,user_emailid,user_phno,user_age,is_active,created_date,modified_date) values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, parameters.get("username"));
			stmt.setString(2, parameters.get("password"));
			stmt.setString(3, parameters.get("email"));
			stmt.setLong(4, Long.parseLong(parameters.get("number")));
			stmt.setString(5, parameters.get("age"));
			stmt.setInt(6, 1);
			stmt.setTimestamp(7, date);
			stmt.setTimestamp(8, date);
			i = stmt.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return i;
	}

	public static void insertTemporarypassword(String password, String username) {
		Connection con = MysqlCon.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update user set temporary_password=? where user_name=?");
			stmt.setString(1, password);
			stmt.setString(2, username);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}

	}

	public Map<String,Integer> checkUserpassword(String uname, String password) {
		int i = 0;
		Map<String,Integer> value=new HashMap<String,Integer>();
		String sql = "SELECT user_password,temporary_password FROM user WHERE user_name= ?";
		String user_password = "";
		String temporary_password = "";
		try {
			try (Connection con = MysqlCon.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
				ps.setString(1, uname);
				try (ResultSet rs = ps.executeQuery();) {
					while (rs.next()) {
						user_password = rs.getString("user_password");
						temporary_password = rs.getString("temporary_password");
					}
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		if (user_password.equals(password)) {
			value.put("result",1);
		} else if (temporary_password.equals(password)) {
			value.put("result",2);
		} else {
			value.put("result",0);}
		return value;
	}

	public String getpassword_History(String username) {
		String str = "";
		String sql = "SELECT user_pasword_history FROM user WHERE user_name= ?";
		try {
			try (Connection con = MysqlCon.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
				ps.setString(1, username);
				try (ResultSet rs = ps.executeQuery();) {
					while (rs.next()) {
						str = rs.getString(1);
					}
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return str;
	}

	public void updatedetail(String password, String password_history, String username) {
		String sql = "update user set user_password=?,user_pasword_history=?  where user_name=?";
		try (Connection con = MysqlCon.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, password);
			ps.setString(2, password_history);
			ps.setString(3, username);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	public HashMap<String, String> checkUser(String uname, String pass) {
		ResultSet rs = null;
		HashMap<String, String> value = new HashMap<>();
		String sql = "select * from user where user_name=?";
		try {
			try (Connection con = MysqlCon.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
				ps.setString(1, uname);
				rs = ps.executeQuery();
				while (rs.next()) {
					value.put("date", rs.getTimestamp("user_lastlogin_time").toString());
					value.put("count", rs.getInt("user_visitcount")+"");
					String password = rs.getString("user_password");
					String temp_password = rs.getString("temporary_password");
					if (password.equals(pass)) {
						value.put("result","normal");
					} else if (temp_password.equals(pass)) {
						value.put("result","mail" );
					}
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return value;
	}

	public void updatelastLogin_Visitcount(java.sql.Timestamp date, int visit_count, String uname) {
		String sql = "update user set user_lastlogin_time=?, user_visitcount=? where user_name=?";
		try (Connection con = MysqlCon.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setTimestamp(1, date);
			ps.setInt(2, visit_count);
			ps.setString(3, uname);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
		
	}
}
