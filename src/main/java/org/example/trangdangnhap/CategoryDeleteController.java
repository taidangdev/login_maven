package org.example.trangdangnhap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.trangdangnhap.model.Category;
import org.example.trangdangnhap.model.User;
import org.example.trangdangnhap.service.CategoryService;
import org.example.trangdangnhap.service.impl.CategoryServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = { "/admin/category/delete" })
public class CategoryDeleteController extends HttpServlet {
    
    CategoryService cateService = new CategoryServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // Kiểm tra đăng nhập
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        User currentUser = (User) session.getAttribute("currentUser");
        
        try {
            String idStr = req.getParameter("id");
            if (idStr == null || idStr.trim().isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/admin/category/list");
                return;
            }
            
            Long id = Long.parseLong(idStr);
            
            // Kiểm tra category có tồn tại và thuộc về user hiện tại không
            Category existingCategory = cateService.findById(id);
            if (existingCategory == null || !existingCategory.getUserId().equals(currentUser.getId())) {
                resp.sendRedirect(req.getContextPath() + "/admin/category/list");
                return;
            }
            
            // Xóa category
            cateService.delete(id);
            
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
            
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        }
    }
}
