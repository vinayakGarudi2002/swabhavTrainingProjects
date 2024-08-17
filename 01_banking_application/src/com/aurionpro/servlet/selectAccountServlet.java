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
	        
	        String selectedAccount = request.getParameter("selectedAccount");

	       
	        if (selectedAccount != null && !selectedAccount.isEmpty()) {
	            long accountNumber = Long.parseLong(selectedAccount);

	           
	            HttpSession session = request.getSession();
	            session.setAttribute("selectedAccount", accountNumber);

	         
	            response.sendRedirect("customerOperation.jsp");
	        } else {
	            
	            response.sendRedirect("customerDashboard.jsp");
	        }
	    }

}
