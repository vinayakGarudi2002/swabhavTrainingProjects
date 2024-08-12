package com.aurionpro.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.database.DbConnection;
import com.aurionpro.model.AccountOperation;

/**
 * Servlet implementation class PerformTransactionServlet
 */
@WebServlet("/PerformTransactionServlet")
public class PerformTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerformTransactionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        String transactionType = request.getParameter("transactionType");
	        double amount = Double.parseDouble(request.getParameter("amount"));
	        long receiverAccountNo = 0;
	        if ("TRANSFER".equals(transactionType)) {
	            receiverAccountNo = Long.parseLong(request.getParameter("receiverAccountNo"));
	        }
           
	        // Assuming user session has the current account number
	        long currentAccountNo = (Long) request.getSession().getAttribute("selectedAccount");

	        try (Connection connection =  DbConnection.connectToDb()) {
	            AccountOperation accountOp = new AccountOperation(connection);
	            
	            String resultMessage = null;

	            if ("CREDIT".equals(transactionType)) {
	                resultMessage = accountOp.credit(currentAccountNo, amount);
	            } else if ("DEBIT".equals(transactionType)) {
	                resultMessage = accountOp.debit(currentAccountNo, amount);
	            } else if ("TRANSFER".equals(transactionType)) {
	                resultMessage = accountOp.accountTransferTransaction(currentAccountNo, receiverAccountNo, amount);
	            }

	            if (resultMessage.contains("successfully")) {
	                request.setAttribute("successMessage", resultMessage);
	            } else {
	                request.setAttribute("errorMessage", resultMessage);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "An error occurred while processing the transaction.");
	        }

	        request.getRequestDispatcher("performTransaction.jsp").forward(request, response);
	    }

}
