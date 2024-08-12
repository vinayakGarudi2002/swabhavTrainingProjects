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
    <title>Customer Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .dashboard-container {
            width: 80%;
            margin: 0 auto;
            margin-top: 50px;
        }

        .header {
            background-color: #4CAF50;
            color: white;
            padding: 20px;
            text-align: center;
            border-radius: 5px 5px 0 0;
        }

        .header h1 {
            margin: 0;
            font-size: 24px;
        }

        .nav {
            display: flex;
            justify-content: space-around;
            background-color: #333;
            padding: 15px;
            border-radius: 0 0 5px 5px;
        }

        .nav a {
            color: white;
            text-decoration: none;
            padding: 14px 20px;
            text-align: center;
            border-radius: 4px;
        }

        .nav a:hover {
            background-color: #ddd;
            color: black;
        }

        .nav a.active {
            background-color: #4CAF50;
            color: white;
        }

        .content {
            margin-top: 30px;
            padding: 20px;
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .content h2 {
            margin-top: 0;
            font-size: 22px;
        }
        
        .account-info {
            margin-top: 20px;
            padding: 10px;
            background-color: #e7f1ff;
            border-radius: 5px;
            border: 1px solid #b3d9ff;
        }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <div class="header">
            <h1>Welcome to Your Dashboard, Customer!</h1>
        </div>
        <a href="customerDashboard.jsp"><button>Back</button></a>
        <div class="nav">
            <a href="viewAccountTransaction.jsp">View Transactions</a>
            <a href="performTransaction.jsp">Perform Transaction</a>
            <a href="editProfile.jsp">Edit Profile</a>
        </div>
        
          <form action="LogoutServlet" method="post">
            <button type="submit">Logout</button>
        </form>

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
    </div>
</body>
</html>
