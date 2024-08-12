<%@page import="com.aurionpro.model.AccountOperation"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@page import="com.aurionpro.model.CustomerComponent"%>
<%@page import="com.aurionpro.operation.CustomerOperation"%>
<%@page import="com.aurionpro.database.DbConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Account</title>
    <link rel="stylesheet" type="text/css" href="static/css/styles.css">
    <style>

    </style>
</head>
<body>
    <div class="form-container">
        <h2>Add Account</h2>

        <form method="POST" action="AddAccountServlet">
            <label for="userId">Select User ID without Account:</label>
            <select id="userId" name="userId" onchange="this.form.submit()">
                <option value="">-- Select User ID --</option>
                <% 
                    try {
                        Connection connection = DbConnection.connectToDb();
                        CustomerOperation customerOperation = new CustomerOperation(connection);
                        List<Integer> userIdsWithoutAccount = customerOperation.getCustomersWithoutAccount();

                        for (int userId : userIdsWithoutAccount) {
                            out.println("<option value='" + userId + "'>" + userId + "</option>");
                        }
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                %>
            </select>
        </form>

        <%
            String selectedUserId = request.getParameter("userId");
            if (selectedUserId != null && !selectedUserId.isEmpty()) {
                try {
                    Connection connection = DbConnection.connectToDb();
                    CustomerOperation customerOperation = new CustomerOperation(connection);
                    CustomerComponent customer = customerOperation.getCustomerById(Integer.parseInt(selectedUserId));

                    if (customer != null) {
                        out.println("<h3>Customer Details:</h3>");
                        out.println("First Name: " + customer.getFirstName() + "<br>");
                        out.println("Last Name: " + customer.getLastName() + "<br>");
                        out.println("Email: " + customer.getEmail() + "<br>");

                        // Generate account number
                        AccountOperation accountOperation = new AccountOperation(connection);
                        long newAccountNo = accountOperation.accountNumberGenerator();
                        String generatedAccountNumber = String.valueOf(newAccountNo);

                        out.println("<form class='form-container' method='POST' action='AddAccountServlet'>");
                        out.println("<input type='hidden' name='userId' value='" + selectedUserId + "'>");
                        out.println("<label for='accountNumber'>Generated Account Number:</label>");
                        out.println("<input type='text' id='accountNumber' name='accountNumber' value='" + generatedAccountNumber + "' readonly><br><br>");
                        out.println("<div class='buttons-container'>");
                        out.println("<button type='submit'>Submit</button>");
                        out.println("<button type='button' onclick=\"window.location.href='adminDashboard.jsp';\">Cancel</button>");
                        out.println("</div>");
                        out.println("</form>");
                    }
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        %>

        <!-- Error and Success messages -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                <c:out value="${errorMessage}"/>
            </div>
        </c:if>

        <c:if test="${not empty successMessage}">
            <div class="success-message">
                <c:out value="${successMessage}"/>
            </div>
        </c:if>
    </div>
</body>
</html>
