package org.example.trangdangnhap.dao.impl;

import org.example.trangdangnhap.dao.CategoryDAO;
import org.example.trangdangnhap.model.Category;
import org.example.trangdangnhap.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    
    private static final String SQL_INSERT = "INSERT INTO Category (name, description, userId, createdAt, updatedAt) VALUES (?, ?, ?, GETDATE(), GETDATE())";
    private static final String SQL_UPDATE = "UPDATE Category SET name = ?, description = ?, updatedAt = GETDATE() WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM Category WHERE id = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM Category WHERE id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM Category ORDER BY createdAt DESC";
    private static final String SQL_FIND_BY_USER_ID = "SELECT * FROM Category WHERE userId = ? ORDER BY createdAt DESC";
    private static final String SQL_CHECK_NAME_EXISTS = "SELECT TOP 1 1 FROM Category WHERE name = ? AND userId = ?";

    @Override
    public boolean insert(Category category) {
        System.out.println("DEBUG DAO: Starting insert for category: " + category.getName());
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setLong(3, category.getUserId());
            
            System.out.println("DEBUG DAO: Executing SQL: " + SQL_INSERT);
            System.out.println("DEBUG DAO: Parameters - name: " + category.getName() + ", description: " + category.getDescription() + ", userId: " + category.getUserId());

            int affected = statement.executeUpdate();
            System.out.println("DEBUG DAO: Rows affected: " + affected);
            
            if (affected > 0) {
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (keys.next()) {
                        category.setId(keys.getLong(1));
                        System.out.println("DEBUG DAO: Generated ID: " + category.getId());
                    }
                }
                System.out.println("DEBUG DAO: Insert successful");
                return true;
            }
            System.out.println("DEBUG DAO: Insert failed - no rows affected");
            return false;
        } catch (SQLException e) {
            System.out.println("ERROR DAO: SQL Exception: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Category category) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setLong(3, category.getId());

            int affected = statement.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            
            statement.setLong(1, id);
            int affected = statement.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Category findById(Long id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCategory(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = statement.executeQuery()) {
            
            while (rs.next()) {
                categories.add(mapResultSetToCategory(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<Category> findByUserId(Long userId) {
        System.out.println("DEBUG DAO: Searching for userId: " + userId);
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_USER_ID)) {
            
            statement.setLong(1, userId);
            System.out.println("DEBUG DAO: Executing query: " + SQL_FIND_BY_USER_ID);
            
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Category cat = mapResultSetToCategory(rs);
                    categories.add(cat);
                    System.out.println("DEBUG DAO: Found category: " + cat.getName() + " (ID: " + cat.getId() + ")");
                }
            }
            System.out.println("DEBUG DAO: Total categories found: " + categories.size());
        } catch (SQLException e) {
            System.out.println("ERROR DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public boolean isNameExists(String name, Long userId) {
        System.out.println("DEBUG DAO: Checking if name exists - name: " + name + ", userId: " + userId);
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CHECK_NAME_EXISTS)) {
            
            statement.setString(1, name);
            statement.setLong(2, userId);
            System.out.println("DEBUG DAO: Executing SQL: " + SQL_CHECK_NAME_EXISTS);
            
            try (ResultSet rs = statement.executeQuery()) {
                boolean exists = rs.next();
                System.out.println("DEBUG DAO: Name exists result: " + exists);
                return exists;
            }
        } catch (SQLException e) {
            System.out.println("ERROR DAO: SQL Exception in isNameExists: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private Category mapResultSetToCategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setName(rs.getString("name"));
        category.setDescription(rs.getString("description"));
        category.setUserId(rs.getLong("userId"));
        
        // Xử lý cột createdAt và updatedAt một cách an toàn
        try {
            category.setCreatedAt(rs.getString("createdAt"));
        } catch (SQLException e) {
            category.setCreatedAt(null);
        }
        
        try {
            category.setUpdatedAt(rs.getString("updatedAt"));
        } catch (SQLException e) {
            category.setUpdatedAt(null);
        }
        
        return category;
    }
}
