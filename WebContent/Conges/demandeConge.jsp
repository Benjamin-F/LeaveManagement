<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			String login = null;
			login = (String) session.getAttribute("sessionUtilisateur");
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
		   			<div class="form-login">
						<h4>Please select your leave date</h4>
					
						<form action="/LeaveManagement/VerificationServlet" method="post">
							<div>
								<input class="form-control input-sm chat-input" name="leaveDate" placeholder="jj/mm/yyyy"></input>
							</div>
							<br/>
							<p class="wrapper" ><input class="btn btn-primary btn-md" name="mySubmit" type="submit" value="Valider" /></p>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</body>
</html>