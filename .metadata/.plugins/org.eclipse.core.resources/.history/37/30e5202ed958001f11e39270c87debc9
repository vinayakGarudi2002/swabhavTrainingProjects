package com.aurionpro.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class selectAccountServlet
 */
@WebServlet("/selectAccountServlet")
public class selectAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectAccountServlet() {
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

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Retrieve the selected account number from the request
	        String selectedAccount = request.getParameter("selectedAccount");

	        // Validate and process the selected account
	        if (selectedAccount != null && !selectedAccount.isEmpty()) {
	            long accountNumber = Long.parseLong(selectedAccount);

	            // Store the selected account in the session
	            HttpSession session = request.getSession();
	            session.setAttribute("selectedAccount", accountNumber);

	            // Redirect to another page or provide feedback
	            response.sendRedirect("customerOperation.jsp");
	        } else {
	            // Handle the case where no account is selected
	            response.sendRedirect("customerDashboard.jsp");
	        }
	    }

}
