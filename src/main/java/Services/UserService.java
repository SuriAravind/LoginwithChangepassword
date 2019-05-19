package Services;

import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.lang.reflect.Type;

import org.apache.log4j.Logger;

import com.ecommerce.user.UserRegistration;
import com.ecommerce.user.dao.UsersDao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

public class UserService 
{
	UsersDao usersDao = new UsersDao();
	final static Logger logger = Logger.getLogger(UserRegistration.class);

	public int isUserPassword(String username, String old_password) {
	     int i=0;
		Map<String,Integer> value= usersDao.checkUserpassword(username, old_password);
        if(value.get("result")==1){
        	i=1;
        }
        else if(value.get("result")==2){
        	i=2;
        }
        else 
        {
        	i=0;
        }
        return i;
	}

	public void updatePasswordhistory(String username, String password) 
	{
		Gson converter = new Gson();
		List<String> myCustomList = new ArrayList<String>();
		String password_history = usersDao.getpassword_History(username);
		if (password_history != null)
		{
            Type type = new TypeToken<List<String>>() {}.getType();
			myCustomList = converter.fromJson(password_history, type);
			if (myCustomList.contains(password)) 
			{
				logger.error("U used one of the existing password");
			} 
			else 
			{
				JsonArray jsonArray = new Gson().toJsonTree(myCustomList).getAsJsonArray();
				if (jsonArray.size() == 3) 
				{
					myCustomList.add(0, password);
					myCustomList = myCustomList.subList(0, 3);
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
		usersDao.updatedetail(password,password_history,username);
     }

	public  String CheckLogin(String uname, String pass) throws SQLException 
	{   
	
	    HashMap<String, String> value=usersDao.checkUser(uname,pass);
		String datevalue=value.get("date");
		java.sql.Timestamp lastLogin = java.sql.Timestamp.valueOf(datevalue);
		int visit_count=Integer.parseInt(value.get("count"));
		String result=value.get("result");
		if(result!=null)
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
	  else 
	  {
		logger.error("error");	
	  }
	   java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	   usersDao.updatelastLogin_Visitcount(date,visit_count,uname);
	   return result;	
	}	
}


