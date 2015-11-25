<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		
		<%
			String login = null;
		
				login = (String) session.getAttribute("sessionUtilisateur");
			
			
		%>	
		<h2>You're authenticated as <%=login %></h2>
		<h3>Please select your leave date</h3>
		<form action="/LeaveManagement/VerificationServlet" method="post">
			
			<div>
				<p>Select date:</p>
				<input type="date" name="leaveDate" min="1900-01-02"></input>
			</div>
			
			<p><input name="mySubmit" type="submit" value="Valider" /></p>
		</form>
		
		<form method="link" action="/LeaveManagement/DeconnexionServlet">
    		<input type="submit" value="Logout"/>
		</form>
		
	</body>
</html>