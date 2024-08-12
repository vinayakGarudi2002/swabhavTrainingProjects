<%@page import="java.sql.Connection"%>
<%@ page import="java.util.List" %>
<%@ page import="com.aurionpro.model.TransactionView" %>
<%@ page import="com.aurionpro.operation.TransactionViewOperation" %>
<%@ page import="com.aurionpro.database.DbConnection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Transactions</title>
    <link rel="stylesheet" type="text/css" href="static/css/styles.css">
</head>
<body>
    <div class="transaction-view-container">
        <h2>View Transactions</h2>
        
        <table>
            <tr>
                <th>Transaction ID</th>
                <th>Receiver Account</th>
                <th>Type of Transaction</th>
                <th>Amount</th>
                <th>Transaction Date</th>
            </tr>
         
           <%
    Long selectedAccount = (Long) session.getAttribute("selectedAccount");
    try {
        Connection connection = DbConnection.connectToDb();
        TransactionViewOperation transactionViewOperation = new TransactionViewOperation(connection);
        List<TransactionView> transactionViews = transactionViewOperation.fetchTransactionsByAccountNumber(selectedAccount);
        int count=1;
        for (TransactionView transaction : transactionViews) {
            out.println("<tr>");
            out.println("<td>" + count + "</td>");
            // Use ternary operator to check for null
            String receiverAccount = transaction.getReceiverAccount() == 0 ? "self" :  Long.toString(transaction.getReceiverAccount());
            out.println("<td>" + receiverAccount + "</td>");
            out.println("<td>" + transaction.getTypeOfTransaction() + "</td>");
            out.println("<td>" + transaction.getAmount() + "</td>");
            out.println("<td>" + transaction.getTransactionDate() + "</td>");
            out.println("</tr>");
            
            count++;
        }
        
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

        </table>
        <a href="customerOperation.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
