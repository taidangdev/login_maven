package org.example.trangdangnhap.dao;

import org.example.trangdangnhap.model.User;

public interface UserDAO {
    boolean registerUser(User user);

    User loginUser(String username, String password);

    boolean isUsernameExists(String username);
    
    boolean updatePassword(Long userId, String newPassword);
    
    boolean isEmailExists(String email);
    
    boolean updatePasswordByEmail(String email, String newPassword);
    
    User getUserByEmail(String email);
}


