<%@page import="java.sql.Connection"%>
<%@ page import="java.util.List" %>
<%@ page import="com.aurionpro.model.TransactionView" %>
<%@ page import="com.aurionpro.operation.TransactionViewOperation" %>
<%@ page import="com.aurionpro.database.DbConnection" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Transactions</title>
    <link rel="stylesheet" type="text/css" href="static/css/styles.css">
</head>
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
/* Add your styling here */
.transaction-view-container {
    padding: 13px;
    margin-top: 363px;
}
</style>
<body>
    <div class="transaction-view-container">
        <h2>View Transactions</h2>
        
        <form class="form-container" method="get">
            <label for="typeOfTransaction">Type of Transaction:</label>
            <select name="typeOfTransaction" id="typeOfTransaction">
                <option value="">All</option>
                <option value="credit">Credit</option>
                <option value="debit">Debit</option>
            </select>
            
            <label for="fromDate">From Date:</label>
            <input type="date" id="fromDate" name="fromDate">
            
            <label for="toDate">To Date:</label>
            <input type="date" id="toDate" name="toDate">
            
            <label for="minAmount">Min Amount:</label>
            <input type="number" step="0.01" id="minAmount" name="minAmount">
            
            <label for="maxAmount">Max Amount:</label>
            <input type="number" step="0.01" id="maxAmount" name="maxAmount">
            
            <button type="submit">Search</button>
        </form>

        <table>
            <tr>
                <th>Transaction ID</th>
                <th>Receiver Account</th>
                <th>Type of Transaction</th>
                <th>Amount</th>
                <th>Transaction Date</th>
            </tr>
            <%
                try {
                    Connection connection = DbConnection.connectToDb();
                    TransactionViewOperation transactionViewOperation = new TransactionViewOperation(connection);
                    
                    String typeOfTransaction = request.getParameter("typeOfTransaction");
                    String fromDateStr = request.getParameter("fromDate");
                    String toDateStr = request.getParameter("toDate");
                    String minAmountStr = request.getParameter("minAmount");
                    String maxAmountStr = request.getParameter("maxAmount");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date fromDate = (fromDateStr != null && !fromDateStr.isEmpty()) ? sdf.parse(fromDateStr) : null;
                    Date toDate = (toDateStr != null && !toDateStr.isEmpty()) ? sdf.parse(toDateStr) : null;
                    Double minAmount = (minAmountStr != null && !minAmountStr.isEmpty()) ? Double.parseDouble(minAmountStr) : null;
                    Double maxAmount = (maxAmountStr != null && !maxAmountStr.isEmpty()) ? Double.parseDouble(maxAmountStr) : null;

                    List<TransactionView> transactionViews = transactionViewOperation.fetchFilteredAccountTransactionViews(
                        typeOfTransaction, fromDate, toDate, minAmount, maxAmount , (Long) session.getAttribute("selectedAccount")
                    );
                    
                    int count =1;

                    for (TransactionView transaction : transactionViews) {
                        out.println("<tr>");
                        out.println("<td>" + count + "</td>");
                        String receiverAccount = transaction.getReceiverAccount() == 0 ? "self" : Long.toString(transaction.getReceiverAccount());
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
