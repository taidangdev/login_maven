<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý Category</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1000px;
            margin: 0 auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }
        .actions {
            margin-bottom: 20px;
            text-align: right;
        }
        .btn {
            padding: 10px 20px;
            background: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-left: 10px;
        }
        .btn:hover {
            background: #0056b3;
        }
        .btn-danger {
            background: #dc3545;
        }
        .btn-danger:hover {
            background: #c82333;
        }
        .btn-warning {
            background: #ffc107;
            color: #212529;
        }
        .btn-warning:hover {
            background: #e0a800;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f8f9fa;
            font-weight: bold;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .empty-message {
            text-align: center;
            padding: 40px;
            color: #666;
        }
        .error-message {
            background: #f8d7da;
            color: #721c24;
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 20px;
            border: 1px solid #f5c6cb;
        }
        .back-link {
            color: #007bff;
            text-decoration: none;
            margin-bottom: 20px;
            display: inline-block;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Quản lý Category</h1>
        
        <a href="${pageContext.request.contextPath}/home" class="back-link">← Quay lại trang chủ</a>
        
        <div class="actions">
            <a href="${pageContext.request.contextPath}/admin/category/add" class="btn">➕ Thêm Category</a>
        </div>


        <c:if test="${not empty error}">
            <div class="error-message">
                <strong>Lỗi:</strong> ${error}
            </div>
        </c:if>

        <c:choose>
            <c:when test="${not empty cateList}">
                <table>
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Tên Category</th>
                            <th>Mô tả</th>
                            <th>Ngày tạo</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cateList}" var="cate" varStatus="STT">
                            <tr>
                                <td>${STT.index + 1}</td>
                                <td><strong>${cate.name}</strong></td>
                                <td>${cate.description}</td>
                                <td>${cate.createdAt}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/category/edit?id=${cate.id}" class="btn btn-warning">✏️ Sửa</a>
                                    <a href="${pageContext.request.contextPath}/admin/category/delete?id=${cate.id}" 
                                       class="btn btn-danger" 
                                       onclick="return confirm('Bạn có chắc chắn muốn xóa category này?')">🗑️ Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="empty-message">
                    <h3>Chưa có category nào</h3>
                    <p>Bạn chưa tạo category nào. Hãy bắt đầu bằng cách tạo category đầu tiên!</p>
                    <a href="${pageContext.request.contextPath}/admin/category/add" class="btn">Tạo Category đầu tiên</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
