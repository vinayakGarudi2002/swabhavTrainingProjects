package com.aurionpro.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.database.DbConnection;
import com.aurionpro.model.CustomerView;
import com.aurionpro.operation.CustomerViewOperation;

/**
 * Servlet implementation class ViewCustomersServlet
 */
@WebServlet("/ViewCustomersServlet")
public class ViewCustomersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCustomersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = DbConnection.connectToDb();
        CustomerViewOperation customerViewOperation = new CustomerViewOperation(connection);

        List<CustomerView> customerViews = customerViewOperation.fetchAllCustomerViews();
        request.setAttribute("customerViews", customerViews);

        try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.getRequestDispatcher("viewCustomers.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
