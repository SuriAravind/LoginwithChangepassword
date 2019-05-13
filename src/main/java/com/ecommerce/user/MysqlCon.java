package com.ecommerce.user;
import java.sql.*;

import org.apache.log4j.Logger;
public class MysqlCon {
	private static String urlstring = "jdbc:mysql://localhost:3306/ecommerce";    
    private static String driverName = "com.mysql.cj.jdbc.Driver";   
    private static String username = "root";   
    private static String password = "root";
    private static Connection con;

    public static Connection getConnection() 
    {
    	Logger log = Logger.getLogger(MysqlCon.class);
    	try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(urlstring, username, password);
            } catch (SQLException ex) {
                log.error(ex); 
            }
        } catch (ClassNotFoundException ex) 
    	{
        	log.error(ex);
        }
        return con;
    }

}
