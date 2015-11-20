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
			if(session.getAttribute("login") == null){
				%>
			  	<jsp:forward page="/Employes/index.jsp" />
			  	<%
			}else{
				login = (String) session.getAttribute("login");
			}
		
		
			//Check
			Boolean booked = (Boolean)request.getAttribute("booked"); 
			if(booked){
				out.println("<h2>You're authenticated as "+login+"</h2>");
				out.println("<h3>Leave status : Success</h3>");
				out.println("<p>Your leave day have been successfuly saved</p>");
				out.println("<p><a href='/LeaveManagement/Conges/demandeConge.jsp'>Set another day.</a></p>");
			}
			else{
				out.println("<h2>You're authenticated as "+login+"</h2>");
				out.println("<h3>Leave status : Failure</h3>");
				out.println("<p>You can't set a leave day for this date.</p>");
				out.println("<p><a href='/LeaveManagement/Conges/demandeConge.jsp'>Try again.</a></p>");
			}	
		%>
	</body>
</html>