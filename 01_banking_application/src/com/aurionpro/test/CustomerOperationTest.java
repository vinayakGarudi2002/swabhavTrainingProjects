package com.aurionpro.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.aurionpro.database.DbConnection;
import com.aurionpro.model.Customer;
import com.aurionpro.model.CustomerComponent;
import com.aurionpro.operation.CustomerOperation;

public class CustomerOperationTest {

    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        Connection connection = null;

        try {
            // Establish the database connection
            connection = dbConnection.connectToDb();

            // Initialize the CustomerOperation class with the connection
            CustomerOperation customerOperation = new CustomerOperation(connection);
            
            List<Integer> arr=customerOperation.getCustomersWithoutAccount();
            
            // get list of customer without account 
//            for(int a :  arr){
//            	System.out.println(a);
//            	System.out.println(customerOperation.getCustomerById(a).getDetails());
//            }
//            
            

            // Test: Create a new customer
            CustomerComponent newCustomer = new Customer("Sham", "Verma", "admin@gmail.com", "password123");
            System.out.println(customerOperation.createCustomer(newCustomer));;
            System.out.println("Customer created successfully.");
//
//            // Test: Read the customer by email
//            CustomerComponent retrievedCustomer = customerOperation.getCustomerByEmail("john.doe@example.com");
//            if (retrievedCustomer != null) {
//                System.out.println("Customer retrieved successfully: " + retrievedCustomer.getFirstName() + " " + retrievedCustomer.getLastName());
//            } else {
//                System.out.println("Customer not found.");
//            }

//            // Test: Update the customer's last name
//            CustomerComponent updatedCustomer = new Customer("John", "Smith", "johny.doe@example.com", "password123");
//            customerOperation.updateCustomer(updatedCustomer);
//            System.out.println("Customer updated successfully.");
//
//            // Verify update by retrieving the updated customer
//            CustomerComponent updatedRetrievedCustomer = customerOperation.getCustomerByEmail("john.doe@example.com");
//            if (updatedRetrievedCustomer != null) {
//                System.out.println("Updated Customer: " + updatedRetrievedCustomer.getFirstName() + " " + updatedRetrievedCustomer.getLastName());
//            }
//
            // Test: Delete the customer by email
//            customerOperation.deleteCustomerByEmail("admin@gmail.com");
//            System.out.println("Customer deleted successfully.");
//
//            // Verify delete by attempting to retrieve the deleted customer
//            CustomerComponent deletedCustomer = customerOperation.getCustomerByEmail("john.doe@example.com");
//            if (deletedCustomer == null) {
//                System.out.println("Customer deletion confirmed.");
//            } else {
//                System.out.println("Customer still exists after deletion.");
//            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            //dbConnection.closeConnection(connection);
        }
    }
}
