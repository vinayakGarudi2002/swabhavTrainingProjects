<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
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

        .error-message {
            color: red;
            font-weight: bold;
        }

        .hidden {
            display: none;
        }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <div class="header">
            <h1>Welcome to Your Dashboard, Customer!</h1>
        </div>

       
        <div class="content">
            <h2>Dashboard Overview</h2>
            <form action="LogoutServlet" method="post">
            <button type="submit">Logout</button>
        </form>
            
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
            <div class="hidden">
           
              <p>Use the navigation links above to view your transactions, perform a new transaction, or edit your profile information.</p>
            </div>
            <% 
                } 
            %>
        </div>
    </div>
</body>
</html>
