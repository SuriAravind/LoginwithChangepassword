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
<a href="./logout">Logout</a>
<a href="changePassword.jsp">Change Password</a>
<form action="forgotpassword" method="post">
<input type="submit">
</form>
</body>
</html>