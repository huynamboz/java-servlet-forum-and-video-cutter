<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
    <link rel="stylesheet" type="text/css" href="./css/login.css">
</head>
<body>

    <div class="container">
        <h2>Login</h2>
        
    	<% if (request.getAttribute("errorMessage") != null) { %>
        	<p class="error-message"><%= request.getAttribute("errorMessage") %></p>
    	<% } %>
    
        <form action="login" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <input type="submit" value="Login">
        </form>
    </div>

</body>
</html>