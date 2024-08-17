<%@page import="java.sql.Connection"%>
<%@ page import="java.util.List" %>
<%@ page import="com.aurionpro.model.CustomerView" %>
<%@ page import="com.aurionpro.operation.CustomerViewOperation" %>
<%@ page import="com.aurionpro.database.DbConnection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Customers</title>
    <link rel="stylesheet" type="text/css" href="static/css/styles.css">
    <style>
       a {
            display: block;
            width: 100%;
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

        .search-bar {
            margin-bottom: 20px;
        }

        .search-bar input[type="text"] {
            padding: 8px;
            width: 200px;
            font-size: 16px;
        }

        .search-bar input[type="submit"] {
            padding: 8px 16px;
            font-size: 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .search-bar input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="customer-view-container">
        <h2>View Customers</h2>
        
        <div class="search-bar">
            <form action="viewCustomers.jsp" method="get">
                <input type="text" name="searchQuery" placeholder="Search by Name or Account No" />
                <input type="submit" value="Search" />
            </form>
        </div>
        
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Account Number</th>
                <th>Balance</th>
            </tr>
            <%
                String searchQuery = request.getParameter("searchQuery");

                try {
                    Connection connection = DbConnection.connectToDb();
                    CustomerViewOperation customerViewOperation = new CustomerViewOperation(connection);
                    List<CustomerView> customerViews = customerViewOperation.fetchCustomerViewsBySearch(searchQuery);
                    
                    for (CustomerView customer : customerViews) {
                        out.println("<tr>");
                        out.println("<td>" + customer.getFirstName() + "</td>");
                        out.println("<td>" + customer.getLastName() + "</td>");
                        out.println("<td>" + customer.getAccountNo() + "</td>");
                        out.println("<td>" + customer.getBalance() + "</td>");
                        out.println("</tr>");
                    }
                    
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
        </table>
        <a href="adminDashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
