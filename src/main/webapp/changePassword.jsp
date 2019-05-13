<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
if(session.getAttribute("username")==null)
{
	response.sendRedirect("./index.jsp");
}
 
%>
<form method="post" action="changePassword">
Password<input type="password" name="oldpassword"><br>
Change Password<input type="password" name="password"><br>
Confirm Password<input type="password" name="confirmpassword"><br>
<input type="submit">
</form>
</body>
</html>