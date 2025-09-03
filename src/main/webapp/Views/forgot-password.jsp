<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quên mật khẩu</title>
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
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }

        .forgot-password-container {
            background: white;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
            text-align: center;
        }

        .forgot-password-header {
            margin-bottom: 30px;
        }

        .forgot-password-header h2 {
            color: #333;
            font-size: 28px;
            font-weight: 600;
            margin-bottom: 10px;
        }

        .forgot-password-header p {
            color: #666;
            font-size: 14px;
            line-height: 1.6;
        }

        .form-group {
            margin-bottom: 20px;
            text-align: left;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: 500;
            font-size: 14px;
        }

        .form-group input[type="email"],
        .form-group input[type="password"] {
            width: 100%;
            padding: 12px 15px;
            border: 2px solid #e1e5e9;
            border-radius: 10px;
            font-size: 14px;
            transition: all 0.3s ease;
            background: #f8f9fa;
        }

        .form-group input:focus {
            outline: none;
            border-color: #667eea;
            background: white;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }

        .password-strength {
            margin-top: 8px;
            font-size: 12px;
            color: #666;
        }

        .password-strength.weak { color: #e74c3c; }
        .password-strength.medium { color: #f39c12; }
        .password-strength.strong { color: #27ae60; }

        .btn {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 10px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            text-decoration: none;
            display: inline-block;
            text-align: center;
            margin-bottom: 15px;
        }

        .btn-primary {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(102, 126, 234, 0.4);
        }

        .btn-secondary {
            background: #f8f9fa;
            color: #667eea;
            border: 2px solid #667eea;
        }

        .btn-secondary:hover {
            background: #667eea;
            color: white;
        }

        .alert {
            padding: 12px 15px;
            border-radius: 8px;
            margin-bottom: 20px;
            font-size: 14px;
            font-weight: 500;
        }

        .alert-danger {
            background: #ffe6e6;
            color: #d63031;
            border: 1px solid #fab1a0;
        }

        .alert-success {
            background: #e6ffe6;
            color: #00b894;
            border: 1px solid #55a3ff;
        }

        .password-requirements {
            background: #f8f9fa;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 20px;
            text-align: left;
        }

        .password-requirements h4 {
            color: #333;
            font-size: 14px;
            margin-bottom: 8px;
        }

        .password-requirements ul {
            list-style: none;
            padding: 0;
        }

        .password-requirements li {
            color: #666;
            font-size: 12px;
            margin-bottom: 4px;
            padding-left: 15px;
            position: relative;
        }

        .password-requirements li::before {
            content: '•';
            color: #667eea;
            position: absolute;
            left: 0;
        }

        .back-links {
            margin-top: 20px;
            text-align: center;
        }

        .back-links a {
            color: #667eea;
            text-decoration: none;
            font-size: 14px;
            margin: 0 10px;
        }

        .back-links a:hover {
            text-decoration: underline;
        }

        .info-box {
            background: #e3f2fd;
            border: 1px solid #2196f3;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 20px;
            text-align: left;
        }

        .info-box h4 {
            color: #1976d2;
            font-size: 14px;
            margin-bottom: 8px;
            display: flex;
            align-items: center;
        }

        .info-box h4::before {
            content: 'ℹ️';
            margin-right: 8px;
        }

        .info-box p {
            color: #1565c0;
            font-size: 12px;
            line-height: 1.4;
        }

        @media (max-width: 480px) {
            .forgot-password-container {
                padding: 30px 20px;
                margin: 10px;
            }
        }
    </style>
</head>
<body>
    <div class="forgot-password-container">
        <div class="forgot-password-header">
            <h2>🔑 Quên mật khẩu</h2>
            <p>Nhập email của bạn để đặt lại mật khẩu mới</p>
        </div>

        <div class="info-box">
            <h4>Hướng dẫn</h4>
            <p>Chỉ cần nhập email đã đăng ký trong hệ thống và mật khẩu mới. Hệ thống sẽ tự động cập nhật mật khẩu cho tài khoản của bạn.</p>
        </div>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/forgot-password" id="forgotPasswordForm">
            <div class="form-group">
                <label for="email">Email đã đăng ký</label>
                <input type="email" id="email" name="email" required placeholder="Nhập email của bạn">
            </div>
            
            <div class="form-group">
                <label for="newPassword">Mật khẩu mới</label>
                <input type="password" id="newPassword" name="newPassword" required placeholder="Nhập mật khẩu mới">
                <div class="password-strength" id="passwordStrength"></div>
            </div>
            
            <div class="form-group">
                <label for="confirmPassword">Xác nhận mật khẩu mới</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required placeholder="Nhập lại mật khẩu mới">
            </div>

            <div class="password-requirements">
                <h4>Yêu cầu mật khẩu mới:</h4>
                <ul>
                    <li>Ít nhất 8 ký tự</li>
                    <li>Chứa chữ hoa và chữ thường</li>
                    <li>Chứa ít nhất một số</li>
                    <li>Chứa ít nhất một ký tự đặc biệt</li>
                </ul>
            </div>
            
            <button type="submit" class="btn btn-primary">Đặt lại mật khẩu</button>
        </form>

        <div class="back-links">
            <a href="${pageContext.request.contextPath}/login">← Quay lại đăng nhập</a>
            <a href="${pageContext.request.contextPath}/register">Đăng ký tài khoản mới</a>
        </div>
    </div>

    <script>
        // Kiểm tra độ mạnh mật khẩu
        document.getElementById('newPassword').addEventListener('input', function() {
            const password = this.value;
            const strengthDiv = document.getElementById('passwordStrength');
            
            if (password.length === 0) {
                strengthDiv.textContent = '';
                strengthDiv.className = 'password-strength';
                return;
            }
            
            let strength = 0;
            let feedback = '';
            
            if (password.length >= 8) strength++;
            if (/[a-z]/.test(password)) strength++;
            if (/[A-Z]/.test(password)) strength++;
            if (/[0-9]/.test(password)) strength++;
            if (/[^A-Za-z0-9]/.test(password)) strength++;
            
            if (strength <= 2) {
                feedback = 'Yếu';
                strengthDiv.className = 'password-strength weak';
            } else if (strength <= 3) {
                feedback = 'Trung bình';
                strengthDiv.className = 'password-strength medium';
            } else {
                feedback = 'Mạnh';
                strengthDiv.className = 'password-strength strong';
            }
            
            strengthDiv.textContent = `Độ mạnh: ${feedback}`;
        });
        
        // Kiểm tra xác nhận mật khẩu
        document.getElementById('forgotPasswordForm').addEventListener('submit', function(e) {
            const newPassword = document.getElementById('newPassword').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            const email = document.getElementById('email').value;
            
            if (newPassword !== confirmPassword) {
                e.preventDefault();
                alert('Mật khẩu mới và xác nhận mật khẩu không giống nhau!');
                return false;
            }
            
            if (newPassword.length < 8) {
                e.preventDefault();
                alert('Mật khẩu mới phải có ít nhất 8 ký tự!');
                return false;
            }
            
            if (!email || email.trim() === '') {
                e.preventDefault();
                alert('Vui lòng nhập email!');
                return false;
            }
        });
    </script>
</body>
</html>
