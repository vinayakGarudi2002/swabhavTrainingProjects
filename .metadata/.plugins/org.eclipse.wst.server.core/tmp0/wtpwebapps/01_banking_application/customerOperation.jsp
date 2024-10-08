<%@page import="com.aurionpro.database.DbConnection"%>
<%@page import="com.aurionpro.model.AccountOperation"%>
<%@page import="com.aurionpro.model.AccountComponent"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" type="text/css" href="static/css/styles.css">
    <title>Customer Dashboard</title>
    <style>
         .dashboard-container {
            width: 80%;
            margin: 0 auto;
            margin-top: 50px;
        }
        
               select, button[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

       
    </style>
</head>
<body>
    <div class="dashboard-container">
        <div class="heade-dashboard-container">
            <h1>Welcome to Your Dashboard, Customer!</h1>
        </div>
        
        <div class="nav">
            <a href="viewAccountTransaction.jsp">View Transactions</a>
            <a href="performTransaction.jsp">Perform Transaction</a>
            <a href="editProfile.jsp">Edit Profile</a>
        </div>
        
         

        <div class="content">
            <h2>Dashboard Overview</h2>
            <p>Use the navigation links above to view your transactions, perform a new transaction, or edit your profile information.</p>
            
            <% 
                // Retrieve the selected account number from the session
                Long selectedAccount = (Long) session.getAttribute("selectedAccount");
            AccountComponent account = new AccountOperation(DbConnection.connectToDb()).getAccountByNumber(selectedAccount);
                
                if (selectedAccount != null) {
            %>
            <div class="account-info">
                <h3>Selected Account</h3>
                <p>Account Number: <%= selectedAccount %></p>
                <p>Balance: <%= account.getBalance() %></p>
            </div>
            <% 
                } else {
            %>
            <p class="error-message">No account selected. Please select an account from the dropdown menu.</p>
            <% 
                } 
            %>
        </div>
        <div class="buttons-container">
        <a href="customerDashboard.jsp"><button type="submit" >Back</button></a>
         <form action="LogoutServlet" method="post">
            <button type="submit">Logout</button>
        </form>
        </div>
    </div>
</body>
</html>
