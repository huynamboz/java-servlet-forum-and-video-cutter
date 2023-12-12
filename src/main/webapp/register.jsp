<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <link rel="stylesheet" type="text/css" href="./css/auth.css">
</head>
<body>
	<% if (request.getAttribute("errorMessage") != null) { %>
    	<p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
	<% } %>
	<main class="auth-container">
	   <h1>Sign Up</h1>
	   <!-- Sign-up form goes here -->
	  <form class="auth-body" id="signupForm" action="RegisterServlet" method="POST">
	    <div class="auth-field">
	      <p>First Name:</p>
	      <input type="text" placeholder="Enter fullname" id="firstname" name="firstname" required aria-label="Enter your full name">
	    </div>
	    
	    <div class="auth-field">
	      <p>Last Name:</p>
	      <input type="text" placeholder="Enter fullname" id="lastname" name="lastname" required aria-label="Enter your full name">
	    </div>
	
	   <div class="auth-field">
	    <p>Username:</p>
	    <input type="username" id="email" placeholder="Enter email" name="username" required aria-label="Enter your email address">
	   </div>
	
	    <div class="auth-field">
	      <p>Password:</p>
	    <input type="password" id="password" placeholder="Enter password" name="password" required aria-label="Enter your password">
	    </div>
	
	    <button type="submit">Sign Up</button>
	  </form>
	  <a href="/forum/login.jsp">Already have an account? Sign in here</a>
	</main>
   

</body>
</html>