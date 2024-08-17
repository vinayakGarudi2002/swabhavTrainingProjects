<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Customer - Banking App</title>
    <link rel="stylesheet" type="text/css" href="static/css/styles.css">
     <style>
     
      a {
            display: block;
            width: 97%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            text-align: center;
            border-radius: 4px;
            text-decoration: none;
            font-size: 16px;
        }

        a:hover {
            background-color: #0056b3;
        }
      
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Add Customer</h2>
        <form action="AddCustomerServlet" method="post">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required>
            
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            
            <button type="submit">Add Customer</button>
        </form>

        <!-- Error message -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                <c:out value="${errorMessage}"/>
            </div>
        </c:if>
        
         <c:if test="${not empty succesMessage}">
            <div class="succes-message">
                <c:out value="${succesMessage}"/>
            </div>
        </c:if>

        <a href="adminDashboard.jsp" class="">Back to Dashboard</a>
    </div>
</body>
</html>