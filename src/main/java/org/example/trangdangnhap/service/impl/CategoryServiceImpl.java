package org.example.trangdangnhap.service.impl;

import org.example.trangdangnhap.dao.CategoryDAO;
import org.example.trangdangnhap.dao.impl.CategoryDAOImpl;
import org.example.trangdangnhap.model.Category;
import org.example.trangdangnhap.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    
    private final CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public boolean insert(Category category) {
        System.out.println("DEBUG SERVICE: Inserting category: " + category.getName());
        System.out.println("DEBUG SERVICE: User ID: " + category.getUserId());
        
        // Validation
        if (category.getName() == null || category.getName().trim().isEmpty()) {
            System.out.println("DEBUG SERVICE: Name is null or empty");
            return false;
        }
        
        if (category.getUserId() == null) {
            System.out.println("DEBUG SERVICE: User ID is null");
            return false;
        }
        
        // Check if name already exists for this user
        boolean nameExists = isNameExists(category.getName(), category.getUserId());
        System.out.println("DEBUG SERVICE: Name exists check result: " + nameExists);
        
        if (nameExists) {
            System.out.println("DEBUG SERVICE: Name already exists, returning false");
            return false;
        }
        
        boolean daoResult = categoryDAO.insert(category);
        System.out.println("DEBUG SERVICE: DAO insert result: " + daoResult);
        return daoResult;
    }

    @Override
    public boolean update(Category category) {
        // Validation
        if (category.getId() == null || category.getName() == null || category.getName().trim().isEmpty()) {
            return false;
        }
        
        if (category.getUserId() == null) {
            return false;
        }
        
        // Check if name already exists for this user (excluding current category)
        Category existingCategory = categoryDAO.findById(category.getId());
        if (existingCategory != null && !existingCategory.getName().equals(category.getName())) {
            if (isNameExists(category.getName(), category.getUserId())) {
                return false;
            }
        }
        
        return categoryDAO.update(category);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        return categoryDAO.delete(id);
    }

    @Override
    public Category findById(Long id) {
        if (id == null) {
            return null;
        }
        return categoryDAO.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public List<Category> findByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        return categoryDAO.findByUserId(userId);
    }

    @Override
    public boolean isNameExists(String name, Long userId) {
        if (name == null || name.trim().isEmpty() || userId == null) {
            return false;
        }
        return categoryDAO.isNameExists(name.trim(), userId);
    }
}
