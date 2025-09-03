package org.example.trangdangnhap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.trangdangnhap.model.User;
import org.example.trangdangnhap.service.CategoryService;
import org.example.trangdangnhap.service.impl.CategoryServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/category/list" })
public class CategoryListController extends HttpServlet {
    
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
                        // Debug: In ra thông tin user
                        System.out.println("DEBUG: Current User ID: " + currentUser.getId());
                        System.out.println("DEBUG: Current User Username: " + currentUser.getUsername());
                        System.out.println("DEBUG: Current User Type: " + currentUser.getClass().getName());
                        
                        // Lấy danh sách category của user hiện tại
                        List<?> cateList = cateService.findByUserId(currentUser.getId());
                        System.out.println("DEBUG: Category List Size: " + (cateList != null ? cateList.size() : "null"));
                        System.out.println("DEBUG: Category List Type: " + (cateList != null ? cateList.getClass().getName() : "null"));
                        
                        if (cateList != null && !cateList.isEmpty()) {
                            System.out.println("DEBUG: First Category: " + cateList.get(0));
                            System.out.println("DEBUG: First Category Class: " + cateList.get(0).getClass().getName());
                        } else {
                            System.out.println("DEBUG: Category list is empty or null");
                        }
                        
                        req.setAttribute("cateList", cateList);
            
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Views/admin/list-category.jsp");
            dispatcher.forward(req, resp);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
            req.setAttribute("error", "Có lỗi xảy ra khi tải danh sách category: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Views/admin/list-category.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
