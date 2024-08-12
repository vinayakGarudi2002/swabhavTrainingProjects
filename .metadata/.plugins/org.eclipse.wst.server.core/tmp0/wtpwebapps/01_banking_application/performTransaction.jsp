<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perform Transaction</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .form-container {
            width: 50%;
            margin: 0 auto;
            margin-top: 50px;
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input, .form-group select {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
        }

        .form-group input[type="submit"], .form-group input[type="button"] {
            width: 48%;
            margin-right: 2%;
        }

        .form-group input[type="button"] {
            margin-right: 0;
        }

        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 20px;
        }

        .success-message {
            color: green;
            text-align: center;
            margin-bottom: 20px;
        }

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
                <input type="number" name="amount" id="amount" step="0.01" min="0" required>
            </div>

            <div class="form-group">
                <input type="submit" value="Submit">
                <input type="button" value="Cancel" onclick="window.location.href='customerOperation.jsp';">
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
