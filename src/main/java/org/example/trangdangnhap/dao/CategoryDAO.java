package org.example.trangdangnhap.dao;

import org.example.trangdangnhap.model.Category;
import java.util.List;

public interface CategoryDAO {
    boolean insert(Category category);
    
    boolean update(Category category);
    
    boolean delete(Long id);
    
    Category findById(Long id);
    
    List<Category> findAll();
    
    List<Category> findByUserId(Long userId);
    
    boolean isNameExists(String name, Long userId);
}
