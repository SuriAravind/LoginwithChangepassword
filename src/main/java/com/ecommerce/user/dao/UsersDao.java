package com.ecommerce.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ecommerce.user.MysqlCon;
import com.ecommerce.util.QueryPropertyUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class UsersDao {

	//QueryPropertyUtils queryPropertyUtils = new QueryPropertyUtils();
	final static Logger logger = Logger.getLogger(QueryPropertyUtils.class);
	public int createUser(Map<String,String> parameters) {
		Connection con = MysqlCon.getConnection();
		PreparedStatement stmt = null;
		int i = 0;
		try {
			stmt = con.prepareStatement("insert into User(user_name,user_password,user_emailid,user_phno,user_age) values(?,?,?,?,?)");
            stmt.setString(1,parameters.get("username"));
			stmt.setString(2,parameters.get("password"));
			stmt.setString(3,parameters.get("email"));
			stmt.setLong(4,Long.parseLong(parameters.get("number")));
			stmt.setString(5,parameters.get("age"));
			i = stmt.executeUpdate();
		} 
		catch (SQLException e) {
		logger.error(e);	 
		} 
		finally 
		{
			try {
				stmt.close();
				con.close();
			    } 
			catch (SQLException e) { 
				logger.error(e);	 
				}
		}
		return i;
	}
}
