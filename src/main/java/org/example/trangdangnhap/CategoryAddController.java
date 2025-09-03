package org.example.trangdangnhap;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet(urlPatterns = { "/admin/category/add" })
public class CategoryAddController extends HttpServlet {
    
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
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/Views/admin/add-category.jsp");
        dispatcher.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // Kiểm tra đăng nhập
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        User currentUser = (User) session.getAttribute("currentUser");
        
        try {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            
            // Lấy thông tin từ form
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            
            // Tạo category mới
            Category category = new Category();
            category.setName(name);
            category.setDescription(description);
            category.setUserId(currentUser.getId());
            
            // Thêm category
            boolean success = cateService.insert(category);
            
            if (success) {
                resp.sendRedirect(req.getContextPath() + "/admin/category/list");
            } else {
                req.setAttribute("error", "Thêm category thất bại. Vui lòng kiểm tra thông tin.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("/Views/admin/add-category.jsp");
                dispatcher.forward(req, resp);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Views/admin/add-category.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
