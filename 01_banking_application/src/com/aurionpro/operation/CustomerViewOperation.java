package com.aurionpro.operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.CustomerView;

public class CustomerViewOperation {
    private Connection connection;

    public CustomerViewOperation(Connection connection) {
        this.connection = connection;
    }

    public List<CustomerView> fetchAllCustomerViews() {
        List<CustomerView> customerViews = new ArrayList<>();
        String query = "SELECT p.first_name, p.last_name, a.account_no, a.balance " +
                       "FROM profile p " +
                       "JOIN userProfile up ON p.profile_id = up.profile_id " +
                       "JOIN account a ON up.user_id = a.user_id";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                long accountNo = resultSet.getLong("account_no");
                double balance = resultSet.getDouble("balance");
                
                CustomerView customerView = new CustomerView(firstName, lastName, accountNo, balance);
                customerViews.add(customerView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerViews;
    }
    
    
    public List<CustomerView> fetchCustomerViewsBySearch(String searchQuery) {
        List<CustomerView> customerViews = new ArrayList<>();
        String query = "SELECT p.first_name, p.last_name, a.account_no, a.balance " +
                       "FROM profile p " +
                       "JOIN userProfile up ON p.profile_id = up.profile_id " +
                       "JOIN account a ON up.user_id = a.user_id ";

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query += "WHERE p.first_name LIKE ? OR p.last_name LIKE ? OR a.account_no LIKE ?";
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                String searchPattern = "%" + searchQuery + "%";
                statement.setString(1, searchPattern);
                statement.setString(2, searchPattern);
                statement.setString(3, searchPattern);
            }

            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                long accountNo = resultSet.getLong("account_no");
                double balance = resultSet.getDouble("balance");
                
                CustomerView customerView = new CustomerView(firstName, lastName, accountNo, balance);
                customerViews.add(customerView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerViews;
    }
    
    
}
