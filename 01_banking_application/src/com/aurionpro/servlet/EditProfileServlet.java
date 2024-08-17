package com.aurionpro.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.database.DbConnection;
import com.aurionpro.model.AlphabeticValidator;
import com.aurionpro.model.Customer;
import com.aurionpro.model.CustomerComponent;
import com.aurionpro.model.EmailValidator;
import com.aurionpro.model.User;
import com.aurionpro.model.ValidationContext;
import com.aurionpro.operation.CustomerOperation;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditProfileServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        String sessionPassword = user.getPassword();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");

        ValidationContext context = new ValidationContext();
        context.setValidator(new AlphabeticValidator());
        String firstResult = context.validate(firstName);
        if(!firstResult.equals("true")) {
        	request.setAttribute("errorMessage",firstResult );
        	 request.getRequestDispatcher("editProfile.jsp").forward(request, response);
        	
        }
        String secondResult = context.validate(lastName);
        if(!secondResult.equals("true")) {
        	request.setAttribute("errorMessage",secondResult );
        	 request.getRequestDispatcher("editProfile.jsp").forward(request, response);
        
        }
        
        
        CustomerOperation customerOperation = new CustomerOperation(DbConnection.connectToDb());
        CustomerComponent customer = customerOperation.getCustomerById(userId);

        if (customer != null) {
            if (sessionPassword.equals(password)) {
                CustomerComponent updatedCustomer = new Customer( firstName, lastName, customer.getEmail(), user.getPassword());
                try {
                    customerOperation.updateCustomer(updatedCustomer);
                    request.setAttribute("successMessage", "Profile updated successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", "An error occurred while updating your profile. Please try again.");
                }
                request.getRequestDispatcher("editProfile.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Incorrect password.");
                request.getRequestDispatcher("editProfile.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
