<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/LeaveManagement/bootstrap/css/bootstrap.min.css">         
<link rel="stylesheet" href="/LeaveManagement/bootstrap/css/login.css">         
<script src="/LeaveManagement/bootstrap/js/bootstrap.min.js"></script> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Leave Management Connection</title>
</head>
	<body>	
		<div class="container">
		   <div class="row">
		        <div class="col-md-offset-5 col-md-4" style="margin-top: 10%;">
		            <div class="form-login">
			            <h4>Please, login.</h4>
			            <form action="/LeaveManagement/AuthentificationServlet" method="post">
				            <input type="text" name="login" class="form-control input-sm chat-input" placeholder="login" ></input>
				            <br/>
				            <input type="password" name="pwd" class="form-control input-sm chat-input" placeholder="password"></input>
				            <br/>
				          
                			<label for="useCookies">Se souvenir de moi: &nbsp;</label>
                			<input type="checkbox" id="useCookies" name="useCookies" />
                			<br/>
				           
				            <p class="wrapper">
				           		<input class="btn btn-primary btn-md" name="mySubmit" type="submit" value="Login" />
				            </p>
			            </form>
		            </div>
		        </div>
		    </div>
		</div>
	</body>
</html>