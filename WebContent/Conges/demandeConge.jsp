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
		
			//Get cookies
			Cookie[] cookies = request.getCookies();
			
			//We check cookies to reset session
			if(cookies !=null){
				for(Cookie cookie : cookies){
			  		if(cookie.getName().equals("login")) 
			  			session.setAttribute("login", cookie.getValue());
			  		if(cookie.getName().equals("pwd")) 
			  			session.setAttribute("pwd", cookie.getValue());
				}
			}
			
			//If session doesn't exist
			if(session.getAttribute("login") == null || session.getAttribute("login") == ""){
				%>
			  	<jsp:forward page="/Employes/index.jsp" />
			  	<%
			}else{
				login = (String) session.getAttribute("login");
			}
			
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
		
		<form method="link" action="/LeaveManagement/Employes/index.jsp">
    		<input type="submit" value="Logout"/>
		</form>
		
	</body>
</html>