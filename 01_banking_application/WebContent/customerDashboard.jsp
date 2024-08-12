<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="static/css/styles.css">
</head>
<body>
    <div class="dashboard-container">
        <div class="header">
            <h1>Net Banking App!!</h1>
        </div>
        <div class="content">
            <h2>Dashboard Overview</h2>
            
            <% 
                // Retrieve the list of account numbers from the session
                ArrayList<Long> accountNumbers = (ArrayList<Long>)session.getAttribute("accounts");

                if (accountNumbers != null && !accountNumbers.isEmpty()) {
            %>
            <form method="POST" action="selectAccountServlet">
                <label for="accountSelect">Select Your Account:</label>
                <select id="accountSelect" name="selectedAccount">
                    <% 
                        for (Long accountNumber : accountNumbers) {
                            out.println("<option value='" + accountNumber + "'>" + accountNumber + "</option>");
                        }
                    %>
                </select>
                <button type="submit">Select Account</button>
            </form>
            <% 
                } else {
            %>
            <p class="error-message">You do not have any accounts.</p>
            <% 
                } 
            %>
            
            <form action="LogoutServlet" method="post">
                <button type="submit">Logout</button>
            </form>
        </div>
    </div>
</body>
</html>
