package com.aurionpro.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.database.DbConnection;
import com.aurionpro.model.AccountOperation;
import com.aurionpro.model.User;
import com.aurionpro.operation.UserOperation;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        
        UserOperation userOperation = new UserOperation();
        User user = userOperation.validateUser(email, password);
        

        if (user != null) {
            
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("accounts",new AccountOperation(DbConnection.connectToDb()).getAccountNumbersByUserId(user.getUserId()) );
            
         
            if (user.getRole().equalsIgnoreCase("admin")) {
                response.sendRedirect("adminDashboard.jsp");
            } else if (user.getRole().equalsIgnoreCase("customer")) {
                response.sendRedirect("customerDashboard.jsp");
            }
        } else {
           
            request.setAttribute("errorMessage", "Invalid email or password. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
