package com.aurionpro.operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.model.Customer;
import com.aurionpro.model.CustomerComponent;

public class CustomerOperation {
    private Connection connection;

    public CustomerOperation(Connection connection) {
        this.connection = connection;
    }

 
    private boolean isEmailExists(String email) throws SQLException {
        String checkEmailSQL = "SELECT COUNT(*) FROM profile WHERE email_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(checkEmailSQL)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

  // user / profile / userProfile
    public boolean createCustomer(CustomerComponent customer) throws SQLException {
        if (isEmailExists(customer.getEmail())) {
            return false; // Email already exists
        }

        String insertProfileSQL = "INSERT INTO profile (first_name, last_name, email_id) VALUES (?, ?, ?)";
        String insertUserSQL = "INSERT INTO user (password, role_id) VALUES (?, ?)";

        try (PreparedStatement profileStmt = connection.prepareStatement(insertProfileSQL, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement userStmt = connection.prepareStatement(insertUserSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

        
            profileStmt.setString(1, customer.getFirstName());
            profileStmt.setString(2, customer.getLastName());
            profileStmt.setString(3, customer.getEmail());
            profileStmt.executeUpdate();

            // Get the generated profile_id
            ResultSet profileKeys = profileStmt.getGeneratedKeys();
            int profileId = -1;
            if (profileKeys.next()) {
                profileId = profileKeys.getInt(1);
            }

     
            userStmt.setString(1, customer.getPassword());
            //  role for customer is '2'
            userStmt.setInt(2, 2); 
            userStmt.executeUpdate();

            // Get the generated user_id
            ResultSet userKeys = userStmt.getGeneratedKeys();
            int userId = -1;
            if (userKeys.next()) {
                userId = userKeys.getInt(1);
            }

            // Insert into userProfile table
            if (profileId != -1 && userId != -1) {
                String insertUserProfileSQL = "INSERT INTO userProfile (user_id, profile_id) VALUES (?, ?)";
                try (PreparedStatement userProfileStmt = connection.prepareStatement(insertUserProfileSQL)) {
                    userProfileStmt.setInt(1, userId);
                    userProfileStmt.setInt(2, profileId);
                    userProfileStmt.executeUpdate();
                }
            }
            return true; // Customer created successfully
        }
    }

    // user / profile / userProfile
    public CustomerComponent getCustomerByEmail(String email) throws SQLException {
        String selectSQL = "SELECT p.first_name, p.last_name, u.password FROM profile p " +
                           "JOIN userProfile up ON p.profile_id = up.profile_id " +
                           "JOIN user u ON up.user_id = u.user_id WHERE p.email_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(selectSQL)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getString("first_name"), rs.getString("last_name"), email, rs.getString("password"));
            }
        }
        return null;
    }


    public void updateCustomer(CustomerComponent customer) throws SQLException {
        String updateProfileSQL = "UPDATE profile SET first_name = ?, last_name = ? WHERE email_id = ?";
        String updateUserSQL = "UPDATE user u JOIN userProfile up ON u.user_id = up.user_id " +
                               "JOIN profile p ON up.profile_id = p.profile_id SET u.password = ? WHERE p.email_id = ?";

        try (PreparedStatement profileStmt = connection.prepareStatement(updateProfileSQL);
             PreparedStatement userStmt = connection.prepareStatement(updateUserSQL)) {

            // Update profile table
            profileStmt.setString(1, customer.getFirstName());
            profileStmt.setString(2, customer.getLastName());
            profileStmt.setString(3, customer.getEmail());
            profileStmt.executeUpdate();

            // Update user table
            userStmt.setString(1, customer.getPassword());
            userStmt.setString(2, customer.getEmail());
            userStmt.executeUpdate();
        }
    }


    public void deleteCustomerByEmail(String email) throws SQLException {
        String selectUserIdSQL = "SELECT user_id FROM userProfile up JOIN profile p ON up.profile_id = p.profile_id WHERE p.email_id = ?";
        String deleteProfileSQL = "DELETE FROM profile WHERE email_id = ?";
        String deleteUserSQL = "DELETE FROM user WHERE user_id = ?";

        try (PreparedStatement selectStmt = connection.prepareStatement(selectUserIdSQL)) {
            selectStmt.setString(1, email);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");

                // Delete the profile record
                try (PreparedStatement deleteProfileStmt = connection.prepareStatement(deleteProfileSQL)) {
                    deleteProfileStmt.setString(1, email);
                    deleteProfileStmt.executeUpdate();
                }

                // Delete the user record
                try (PreparedStatement deleteUserStmt = connection.prepareStatement(deleteUserSQL)) {
                    deleteUserStmt.setInt(1, userId);
                    deleteUserStmt.executeUpdate();
                }
            }
        }
    }


    public List<Integer> getCustomersWithoutAccount() {
        List<Integer> userIds = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            
            String sql = "SELECT u.user_id FROM user u " +
                         "LEFT JOIN account a ON u.user_id = a.user_id " +
                         "JOIN roles r ON u.role_id = r.role_id " +
                         "WHERE a.account_no IS NULL AND r.role_name = 'customer'";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userIds.add(resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
              
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userIds;
    }
    
    public List<Integer> getAnyCustomersWithWithoutAccount() {
        List<Integer> userIds = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            
            String sql = "SELECT u.user_id FROM user u " +
                         "JOIN roles r ON u.role_id = r.role_id " +
                         "WHERE r.role_name = 'customer'";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userIds.add(resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
              
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userIds;
    }

    public CustomerComponent getCustomerById(int userId) {
    	CustomerComponent customer = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            
            String sql = "SELECT p.first_name, p.last_name, p.email_id  FROM profile p " +
                         "JOIN userProfile up ON p.profile_id = up.profile_id " +
                         "WHERE up.user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email_id");
                customer = new Customer(firstName, lastName, email,null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }





}
