<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            color: #333;
        }

        .navbar {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            padding: 15px 30px;
            box-shadow: 0 2px 20px rgba(0, 0, 0, 0.1);
            position: sticky;
            top: 0;
            z-index: 1000;
        }

        .nav-container {
            max-width: 1200px;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .nav-brand {
            font-size: 24px;
            font-weight: 700;
            color: #667eea;
            text-decoration: none;
        }

        .nav-user {
            display: flex;
            align-items: center;
            gap: 20px;
        }

        .nav-actions {
            display: flex;
            gap: 10px;
            align-items: center;
        }

        .user-info {
            text-align: right;
        }

        .user-name {
            font-weight: 600;
            color: #333;
            font-size: 16px;
        }

        .user-email {
            color: #666;
            font-size: 14px;
        }

        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
        }

        .btn-danger {
            background: #e74c3c;
            color: white;
        }

        .btn-danger:hover {
            background: #c0392b;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(231, 76, 60, 0.4);
        }

        .main-content {
            max-width: 1200px;
            margin: 50px auto;
            padding: 0 30px;
        }

        .welcome-section {
            background: white;
            border-radius: 20px;
            padding: 40px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            text-align: center;
            margin-bottom: 30px;
        }

        .welcome-icon {
            width: 80px;
            height: 80px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 20px;
            font-size: 32px;
            color: white;
        }

        .welcome-title {
            font-size: 32px;
            font-weight: 700;
            color: #333;
            margin-bottom: 10px;
        }

        .welcome-subtitle {
            font-size: 18px;
            color: #666;
            margin-bottom: 30px;
        }

        .user-details {
            background: #f8f9fa;
            border-radius: 15px;
            padding: 25px;
            margin-bottom: 30px;
        }

        .detail-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 0;
            border-bottom: 1px solid #e1e5e9;
        }

        .detail-item:last-child {
            border-bottom: none;
        }

        .detail-label {
            font-weight: 600;
            color: #333;
        }

        .detail-value {
            color: #667eea;
            font-weight: 500;
        }

        .features-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
            margin-top: 30px;
        }

        .feature-card {
            background: white;
            border-radius: 15px;
            padding: 25px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
        }

        .feature-card:hover {
            transform: translateY(-5px);
        }

        .feature-icon {
            width: 50px;
            height: 50px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border-radius: 12px;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 15px;
            font-size: 20px;
            color: white;
        }

        .feature-title {
            font-size: 18px;
            font-weight: 600;
            color: #333;
            margin-bottom: 10px;
        }

        .feature-description {
            color: #666;
            font-size: 14px;
            line-height: 1.5;
        }

        @media (max-width: 768px) {
            .nav-container {
                flex-direction: column;
                gap: 15px;
            }

            .nav-user {
                flex-direction: column;
                gap: 10px;
            }

            .user-info {
                text-align: center;
            }

            .main-content {
                padding: 0 20px;
                margin: 30px auto;
            }

            .welcome-section {
                padding: 30px 20px;
            }

            .welcome-title {
                font-size: 24px;
            }

            .features-grid {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
    <c:choose>
        <c:when test="${empty sessionScope.currentUser}">
            <c:redirect url="${pageContext.request.contextPath}/login"/>
        </c:when>
        <c:otherwise>
            <nav class="navbar">
                <div class="nav-container">
                    <a href="${pageContext.request.contextPath}/home" class="nav-brand">
                        🏠 Trang chủ
                    </a>
                    <div class="nav-user">
                        <div class="user-info">
                            <div class="user-name">${sessionScope.currentUser.username}</div>
                            <div class="user-email">${sessionScope.currentUser.email}</div>
                        </div>
                        <div class="nav-actions">
                            <a href="${pageContext.request.contextPath}/change-password" class="btn btn-secondary">
                                🔐 Đổi mật khẩu
                            </a>
                            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">
                                Đăng xuất
                            </a>
                        </div>
                    </div>
                </div>
            </nav>

            <div class="main-content">
                <div class="welcome-section">
                    <div class="welcome-icon">👋</div>
                    <h1 class="welcome-title">Xin chào, ${sessionScope.currentUser.username}!</h1>
                    <p class="welcome-subtitle">Chào mừng bạn đến với hệ thống quản lý</p>
                    
                    <div class="user-details">
                        <div class="detail-item">
                            <span class="detail-label">Tên đăng nhập:</span>
                            <span class="detail-value">${sessionScope.currentUser.username}</span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Email:</span>
                            <span class="detail-value">${sessionScope.currentUser.email}</span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Trạng thái:</span>
                            <span class="detail-value">🟢 Đã đăng nhập</span>
                        </div>
                    </div>
                </div>

                <div class="features-grid">
                    <div class="feature-card">
                        <div class="feature-icon">🔐</div>
                        <h3 class="feature-title">Bảo mật</h3>
                        <p class="feature-description">
                            Hệ thống được bảo vệ với các biện pháp bảo mật tiên tiến, 
                            đảm bảo thông tin của bạn luôn an toàn.
                        </p>
                    </div>
                    
                    <div class="feature-card">
                        <div class="feature-icon">⚡</div>
                        <h3 class="feature-title">Hiệu suất cao</h3>
                        <p class="feature-description">
                            Giao diện được tối ưu hóa để mang lại trải nghiệm 
                            mượt mà và nhanh chóng cho người dùng.
                        </p>
                    </div>
                    
                    <div class="feature-card">
                        <div class="feature-icon">📱</div>
                        <h3 class="feature-title">Responsive</h3>
                        <p class="feature-description">
                            Giao diện thích ứng hoàn hảo trên mọi thiết bị, 
                            từ máy tính đến điện thoại di động.
                        </p>
                    </div>
                    
                    <div class="feature-card">
                        <div class="feature-icon">📚</div>
                        <h3 class="feature-title">Quản lý Category</h3>
                        <p class="feature-description">
                            Quản lý các danh mục của bạn một cách dễ dàng và hiệu quả.
                            Tạo, sửa, xóa category theo ý muốn.
                        </p>
                        <div style="margin-top: 15px;">
                            <a href="${pageContext.request.contextPath}/admin/category/list" 
                               class="btn" 
                               style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 8px 16px; font-size: 12px;">
                                🚀 Quản lý Category
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>


