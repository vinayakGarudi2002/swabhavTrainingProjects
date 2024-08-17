<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Banking App</title>
    <link rel="stylesheet" type="text/css" href="static/css/styles.css">
    
   
</head>
<body>
    <div class="dashboard-container">
        <h2>Admin Dashboard</h2>
        <div class="welcome-message">
            <c:out value="Welcome, ${user.getRole()}!"></c:out>
        </div>
        <ul class="admin-actions">
            <li><a href="addCustomer.jsp">Add Customer</a></li>
            <li><a href="viewCustomers.jsp">View All Customers</a></li>
            <li><a href="addAccount.jsp">Add Account</a></li>
            <li><a href="viewTransactions.jsp">View All Transactions</a></li>
        </ul>
        <form action="LogoutServlet" method="post">
            <button type="submit">Logout</button>
        </form>
        
          <c:if test="${not empty successMessage}">
            <div class="success-message">
                <c:out value="${succesMessage}"/>
            </div>
        </c:if>
    </div>
</body>
</html>
