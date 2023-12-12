<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
    <link rel="stylesheet" type="text/css" href="./css/auth.css">
</head>
<body>
	
  <main class="auth-container">
    <h1>Sign In</h1>
    <!-- Sign-in form goes here -->
    <form class="auth-body" action="/forum/login" method="post" id="form-sign-in">
      <div class="auth-field">
        <p>Username:</p>
        <input type="text" placeholder="Enter email" name="username" required>
      </div>
      <div class="auth-field">
        <p>Password:</p>
        <input type="password" placeholder="Enter password" name="password" required id="pass">
      </div>
      <% if (request.getAttribute("errorMessage") != null) { %>
      	<p class="error-message"><%= request.getAttribute("errorMessage") %></p>
  		<% } %>
      <button type="submit" id="submitSignIn">Sign In</button>
    </form>

    <a href="/forum/register.jsp">Don't have an account? Sign up here</a>
  </main>
</body>
</html>