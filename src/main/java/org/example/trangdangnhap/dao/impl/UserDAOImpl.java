package org.example.trangdangnhap.dao.impl;

import org.example.trangdangnhap.dao.UserDAO;
import org.example.trangdangnhap.model.User;
import org.example.trangdangnhap.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAOImpl implements UserDAO {
    private static final String SQL_INSERT = "INSERT INTO [User] (username, password, email) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_LOGIN = "SELECT TOP 1 * FROM [User] WHERE username = ? AND password = ?";
    private static final String SQL_CHECK_EXISTS = "SELECT TOP 1 1 FROM [User] WHERE username = ?";
    private static final String SQL_UPDATE_PASSWORD = "UPDATE [User] SET password = ? WHERE id = ?";
    private static final String SQL_CHECK_EMAIL_EXISTS = "SELECT TOP 1 1 FROM [User] WHERE email = ?";
    private static final String SQL_UPDATE_PASSWORD_BY_EMAIL = "UPDATE [User] SET password = ? WHERE email = ?";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT TOP 1 * FROM [User] WHERE email = ?";

    @Override
    public boolean registerUser(User user) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());

            int affected = statement.executeUpdate();
            if (affected > 0) {
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (keys.next()) {
                        user.setId(keys.getLong(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User loginUser(String username, String password) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_LOGIN)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    try {
                        int role = rs.getInt("roleId");
                        if (!rs.wasNull()) {
                            user.setRoleId(role);
                        }
                    } catch (SQLException ignore) {
                    }
                    return user;
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isUsernameExists(String username) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHECK_EXISTS)) {
            statement.setString(1, username);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePassword(Long userId, String newPassword) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setLong(2, userId);

            int affected = statement.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isEmailExists(String email) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHECK_EMAIL_EXISTS)) {
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePasswordByEmail(String email, String newPassword) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PASSWORD_BY_EMAIL)) {
            statement.setString(1, newPassword);
            statement.setString(2, email);

            int affected = statement.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_EMAIL)) {
            statement.setString(1, email);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    try {
                        int role = rs.getInt("roleId");
                        if (!rs.wasNull()) {
                            user.setRoleId(role);
                        }
                    } catch (SQLException ignore) {
                    }
                    return user;
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}


