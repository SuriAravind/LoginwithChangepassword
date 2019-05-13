package com.ecommerce.user;
import java.lang.reflect.Type;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
public class samplejava {

	public static void main(String[] args) {
		List myCustomList =new ArrayList(); 
		   Gson converter = new Gson();
	    JsonParser jp=new JsonParser();
	    for (int i=0; i < 3; i++)
	    {
	    	myCustomList.add(i);     
		}
	    myCustomList.add(0,4);
	    myCustomList=myCustomList.subList(0,3);
	    System.out.println(new Gson().toJson(myCustomList).toString());
	    String temp="[\"123\"]";
	    Type type = new TypeToken<List<String>>(){}.getType();
	    myCustomList = converter.fromJson(temp, type ); 
	    JsonArray jsonArray  = new Gson().toJsonTree(myCustomList).getAsJsonArray();
	    System.out.println(jsonArray);
	}
}
