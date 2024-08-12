package com.aurionpro.operation;


import com.aurionpro.model.User;
import com.aurionpro.database.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOperation {
	
	

    public User validateUser(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = DbConnection.connectToDb();
            String sql = "SELECT u.user_id, u.password, r.role_name FROM user u " +
                         "JOIN roles r ON u.role_id = r.role_id " +
                         "JOIN userProfile up ON u.user_id = up.user_id " +
                         "JOIN profile p ON up.profile_id = p.profile_id " +
                         "WHERE p.email_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String dbPassword = resultSet.getString("password");
                if (dbPassword.equals(password)) {
                    int userId = resultSet.getInt("user_id");
                    String role = resultSet.getString("role_name");
                    user = new User(userId, email, dbPassword, role);
                }
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
        return user;
    }
}

