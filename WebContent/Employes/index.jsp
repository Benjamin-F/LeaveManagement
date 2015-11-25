<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Leave Management Connection</title>
	</head>
	<body>	
		<h2>Please enter your credentials to access the leave management system</h2>
		<form action="/LeaveManagement/AuthentificationServlet" method="post">

			<div>
				<p>Login:</p>
				<input type="text" name="login"></input>
			</div>
			
			<div>
				<p>Password:</p>
				<input type="password" name="pwd"></input>
			</div>
			
			<p><input name="mySubmit" type="submit" value="Valider" /></p>		
		</form>
	</body>
</html>