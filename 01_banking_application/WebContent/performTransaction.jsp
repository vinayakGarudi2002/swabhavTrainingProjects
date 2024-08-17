<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="static/css/styles.css">
    <title>Perform Transaction</title>
    <style>
        
        #transfer-fields {
            display: none;
        }
    </style>
    <script>
        function toggleTransferFields() {
            var transactionType = document.getElementById("transactionType").value;
            var transferFields = document.getElementById("transfer-fields");
            if (transactionType === "TRANSFER") {
                transferFields.style.display = "block";
            } else {
                transferFields.style.display = "none";
            }
        }
    </script>
</head>
<body>
    <div class="form-container">
        <h2>Perform Transaction</h2>

        <form action="PerformTransactionServlet" method="post">
            <div class="form-group">
                <label for="transactionType">Transaction Type:</label>
                <select name="transactionType" id="transactionType" onchange="toggleTransferFields()" required>
                    <option value="CREDIT">Credit</option>
                    <option value="DEBIT">Debit</option>
                    <option value="TRANSFER">Transfer</option>
                </select>
            </div>

            <div id="transfer-fields">
                <div class="form-group">
                    <label for="receiverAccountNo">Receiver Account Number:</label>
                    <input type="number" name="receiverAccountNo" id="receiverAccountNo">
                </div>
            </div>

            <div class="form-group">
                <label for="amount">Amount:</label>
                <input type="number" name="amount" id="amount" step="0.01" min="1" required>
            </div>

            <div class="form-group">
            <div class='buttons-container'>
                <button type="submit" value="Submit" type="button">Submit</button>
                <button type="button" value="Cancel" onclick="window.location.href='customerOperation.jsp';">Cancel</button>
            </div>
               </div>

            <div class="error-message">
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <%= request.getAttribute("errorMessage") %>
                <% } %>
            </div>

            <div class="success-message">
                <% if (request.getAttribute("successMessage") != null) { %>
                    <%= request.getAttribute("successMessage") %>
                <% } %>
            </div>
        </form>
    </div>
</body>
</html>
