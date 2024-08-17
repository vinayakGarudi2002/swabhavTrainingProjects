<%@page import="com.aurionpro.model.AccountOperation"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@page import="com.aurionpro.model.CustomerComponent"%>
<%@page import="com.aurionpro.operation.CustomerOperation"%>
<%@page import="com.aurionpro.database.DbConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Account</title>
    <link rel="stylesheet" type="text/css" href="static/css/styles.css">
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
        %>
                        <h3>Customer Details:</h3>
                        <p>First Name: <%= customer.getFirstName() %></p>
                        <p>Last Name: <%= customer.getLastName() %></p>
                        <p>Email: <%= customer.getEmail() %></p>

                        <form class="form-container" method="POST" action="AddAccountServlet">
                            <input type="hidden" name="userId" value="<%= selectedUserId %>">
                          

                            <% 
                                AccountOperation accountOperation = new AccountOperation(connection);
                                long newAccountNo = accountOperation.accountNumberGenerator();
                                String generatedAccountNumber = String.valueOf(newAccountNo);
                            %>
                            <label for="accountNumber">Generated Account Number:</label>
                            <input type="text" id="accountNumber" name="accountNumber" value="<%= generatedAccountNumber %>" readonly>
                             <label for="amount">Initial Balance:</label>
                            <input type="number" name="amount" id="amount" value="<%= 500 %>" step="0.01" min="500" required>
                            <div class="buttons-container">
                                <button type="submit">Submit</button>
                                <button type="button" onclick="window.location.href='adminDashboard.jsp';">Cancel</button>
                            </div>
                        </form>
        <% 
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
                <c:out value="${succesMessage}"/>
            </div>
        </c:if>
    </div>
</body>
</html>
