package com.aurionpro.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.database.DbConnection;
import com.aurionpro.model.Account;
import com.aurionpro.model.AccountComponent;
import com.aurionpro.model.AccountOperation;

/**
 * Servlet implementation class AddAccountServlet
 */
@WebServlet("/AddAccountServlet")
public class AddAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAccountServlet() {
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

	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String userId = request.getParameter("userId");
	        String accountNumberStr = request.getParameter("accountNumber");
	        String initialBalance = (request.getParameter("amount"));

	        if (userId == null || userId.isEmpty() || accountNumberStr == null || accountNumberStr.isEmpty()||initialBalance==null) {
	            request.setAttribute("errorMessage", "User ID or Account Number is missing.");
	            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
	            return;
	        }

	        long accountNumber = Long.parseLong(accountNumberStr);
	        int customerId = Integer.parseInt(userId);

	        Connection connection = null;
	        try {
	            connection = DbConnection.connectToDb();
	            AccountOperation accountOperation = new AccountOperation(connection);

	           
	            AccountComponent newAccount = new Account(accountNumber, Double.parseDouble(initialBalance), customerId);
	            String message=accountOperation.createAccount(newAccount);
                // System.out.println(message);
                 if(message.equals("true")) {
                	 request.setAttribute("succesMessage", "Account Succesfully Created");
                	 request.getRequestDispatcher("viewCustomers.jsp").forward(request, response);
                 }else {
                	 request.setAttribute("errorMessage", message);
                 }
	           
	            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("errorMessage", "An error occurred while creating the account. Please try again.");
	            request.getRequestDispatcher("addAccount.jsp").forward(request, response);
	        } finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

}
