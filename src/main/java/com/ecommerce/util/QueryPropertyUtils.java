package com.ecommerce.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;


public class QueryPropertyUtils {
	final static Logger logger = Logger.getLogger(QueryPropertyUtils.class);
	private Properties  prop = new Properties();
	
	public QueryPropertyUtils()
	{

	}
	
	 {
		try {
			prop.load(this.getClass().getResourceAsStream("query.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}
	public String getProperty(String key)
	{
		return prop.getProperty(key, "");
	}
	
}
