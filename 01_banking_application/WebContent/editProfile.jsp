<%@page import="com.aurionpro.model.User"%>
<%@page import="com.aurionpro.database.DbConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page import="com.aurionpro.model.CustomerComponent" %>
<%@ page import="com.aurionpro.operation.CustomerOperation" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
    <script type="text/javascript">
        function enablePasswordField() {
            document.getElementById("password").style.display = "block";
            document.getElementById("submitButton").style.display = "block";
            
        }
    </script>
    
        <link rel="stylesheet" type="text/css" href="static/css/styles.css">
</head>
<body>
    <%
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        Connection connection = DbConnection.connectToDb();
        CustomerOperation customerOperation = new CustomerOperation(connection);
        CustomerComponent customer = customerOperation.getCustomerById(userId);

        String successMessage = (String) request.getAttribute("successMessage");
        String errorMessage = (String) request.getAttribute("errorMessage");
        %>
       
    <%
        

        if (customer != null) {
    %>
       
        <form class="form-container" action="EditProfileServlet" method="post">
         <h2>Edit Profile</h2>
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="<%= customer.getFirstName() %>" oninput="enablePasswordField()"><br><br>
            
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" value="<%= customer.getLastName() %>" oninput="enablePasswordField()"><br><br>
            
            <div  id="password" style="display:none;>
            <label for="password" ">Password:</label>
            <input type="password" name="password" >
            </div><br><br>
            <div class='buttons-container'>
            <button type="submit" id="submitButton" value="Submit" style="display:none;">Submit</button>
            <button type="button" id="cancelButton"  onclick="window.location.href='customerOperation.jsp'">Cancel</button>
        </div>
         <div>
     <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>
    </div>
    <div>
     <c:if test="${not empty successMessage}">
            <div class="succes-message">${successMessage}</div>
        </c:if>
    </div>
        </form>
        
    <%
        } else {
            out.println("Customer not found.");
        }
    %>
   
</body>
</html>
