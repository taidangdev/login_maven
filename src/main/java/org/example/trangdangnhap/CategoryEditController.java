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

@WebServlet(urlPatterns = { "/admin/category/edit" })
public class CategoryEditController extends HttpServlet {
    
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
            Category existingCategory = cateService.findById(id);
            
            if (existingCategory == null) {
                resp.sendRedirect(req.getContextPath() + "/admin/category/list");
                return;
            }
            
            // Kiểm tra quyền: chỉ user sở hữu category mới được sửa
            if (!existingCategory.getUserId().equals(currentUser.getId())) {
                resp.sendRedirect(req.getContextPath() + "/admin/category/list");
                return;
            }
            
            req.setAttribute("category", existingCategory);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Views/admin/edit-category.jsp");
            dispatcher.forward(req, resp);
            
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        }
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
            String idStr = req.getParameter("id");
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            
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
            
            // Cập nhật thông tin
            existingCategory.setName(name);
            existingCategory.setDescription(description);
            
            // Lưu thay đổi
            boolean success = cateService.update(existingCategory);
            
            if (success) {
                resp.sendRedirect(req.getContextPath() + "/admin/category/list");
            } else {
                req.setAttribute("error", "Cập nhật category thất bại. Vui lòng kiểm tra thông tin.");
                req.setAttribute("category", existingCategory);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/Views/admin/edit-category.jsp");
                dispatcher.forward(req, resp);
            }
            
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Views/admin/edit-category.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
