<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Banking App</title>
    <link rel="stylesheet" type="text/css" href="static/css/styles.css">
    <style type="text/css">
     .dashboard-container {
            width: 80%;
            margin: 0 auto;
            margin-top: 50px;
        }
        
    </style>
</head>
<body>
 <div class="dashboard-container">
 <div class="heade-dashboard-container">
            <h1>Welcome to ICICI Net Banking !</h1>
        </div>
    <div class="">
        <h2>Login</h2>
        <form class="form-container" action="LoginServlet" method="post">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Login</button>
        </form>
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>
    </div>
    
    </div>
</body>
</html>