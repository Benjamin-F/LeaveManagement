<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<link rel="stylesheet" href="/LeaveManagement/bootstrap/css/bootstrap.min.css">         
<link rel="stylesheet" href="/LeaveManagement/bootstrap/css/login.css">
<script src="/LeaveManagement/bootstrap/js/bootstrap.min.js"></script> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<%
			String login = (String) session.getAttribute("sessionUtilisateur");
		
			//Check
			Boolean booked = (Boolean)request.getAttribute("booked"); 
			String statusMessage = (String)request.getAttribute("statusMessage");
		%>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
		    	<div class="navbar-form navbar-left">
		      		<h3>You're authenticated as <%=login %></h3>
		      	</div>
	      		<div class="navbar-text navbar-right">
		      		<form method="link" action="/LeaveManagement/DeconnexionServlet">
		    			<input class="form-control" type="submit" value="Logout"/>
					</form>
				</div>
		  	</div>
		</nav>
		
		<div class="container">
		   <div class="row">
		   		<div class="col-md-offset-5 col-md-4" style="margin-top: 10%;">
		   			<% if(booked){ %>
		   			<div class="alert alert-success" role="alert">
		   				<p><%=statusMessage %><br/><a href='/LeaveManagement/Conges/demandeConge.jsp'>Set another day.</a></p>
		   			</div>
		   			<%}
		   			else{
		   				%>
		   				<div class="alert alert-danger" role="alert">
		   					<p><%=statusMessage %><br/><a href='/LeaveManagement/Conges/demandeConge.jsp'>Try again.</a></p>
		   				</div>
		   				<%
		   			}
		   			%>
				</div>
			</div>
		</div>
	</body>
</html>