package com.jap.jukebox.daoImpl;

import com.jap.jukebox.dao.UserInterface;
import com.jap.jukebox.model.User;
import com.jap.jukebox.utility.ConnectDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;

public class UserService implements UserInterface {
    @Override
    public void registerUser(String username , String password) {
        String query = "INSERT INTO users (Username,  Password , CreatedDate) VALUES (?,?, ?)";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            LocalDateTime now = LocalDateTime.now();
            // Convert LocalDateTime to Timestamp
            Timestamp timestamp = Timestamp.valueOf(now);

            // Set the timestamp in the prepared statement
            stmt.setTimestamp(3, timestamp);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE Username = ?";
        if (query != null) {
            try (Connection connection = ConnectDatabase.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query)) {

                stmt.setString(1, username);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String storedHash = rs.getString("Password");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @Override
    public User getUserByUsernameOrEmail(String username) {
        User user = null;
        String query = "SELECT * FROM users WHERE Username = ?";

        try (Connection connection = ConnectDatabase.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setUsername(rs.getString("Username"));


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
