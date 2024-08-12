<%@page import="com.aurionpro.model.User"%>
<%@page import="com.aurionpro.database.DbConnection"%>
<%@page import="java.sql.Connection"%>
<%@ page import="com.aurionpro.model.CustomerComponent" %>
<%@ page import="com.aurionpro.operation.CustomerOperation" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

        if (successMessage != null) {
    %>
        <p style="color:green;"><%= successMessage %></p>
    <%
        } else if (errorMessage != null) {
    %>
        <p style="color:red;"><%= errorMessage %></p>
    <%
        }

        if (customer != null) {
    %>
        <h2>Edit Profile</h2>
        <form action="EditProfileServlet" method="post">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="<%= customer.getFirstName() %>" oninput="enablePasswordField()"><br><br>
            
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" value="<%= customer.getLastName() %>" oninput="enablePasswordField()"><br><br>
            
            <div  id="password" style="display:none;>
            <label for="password" ">Password:</label>
            <input type="password" name="password" >
            </div><br><br>
            
            <input type="submit" id="submitButton" value="Submit" style="display:none;">
            <button type="button" id="cancelButton"  onclick="window.location.href='customerOperation.jsp'">Cancel</button>
        </form>
    <%
        } else {
            out.println("Customer not found.");
        }
    %>
</body>
</html>
