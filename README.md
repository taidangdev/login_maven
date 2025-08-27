# 🚀 Hệ thống Quản lý Đăng nhập/Đăng ký

[![Java](https://img.shields.io/badge/Java-24-orange.svg)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![Servlet](https://img.shields.io/badge/Servlet-6.1-green.svg)](https://jakarta.ee/specifications/servlet/)
[![JSP](https://img.shields.io/badge/JSP-3.1-yellow.svg)](https://jakarta.ee/specifications/pages/)
[![SQL Server](https://img.shields.io/badge/SQL%20Server-2022-red.svg)](https://www.microsoft.com/en-us/sql-server/)

> Một hệ thống quản lý đăng nhập/đăng ký hiện đại được xây dựng bằng Java Servlet, JSP và SQL Server với giao diện người dùng đẹp mắt và responsive.

## 📋 Mục lục

- [✨ Tính năng](#-tính-năng)
- [🛠️ Công nghệ sử dụng](#️-công-nghệ-sử-dụng)
- [📦 Cài đặt](#-cài-đặt)
- [🚀 Chạy ứng dụng](#-chạy-ứng-dụng)
- [📁 Cấu trúc dự án](#-cấu-trúc-dự-án)
- [🎨 Giao diện](#-giao-diện)
- [🔧 Cấu hình](#-cấu-hình)
- [📝 API Endpoints](#-api-endpoints)
- [🤝 Đóng góp](#-đóng-góp)
- [📄 Giấy phép](#-giấy-phép)

## ✨ Tính năng

### 🔐 Xác thực và Bảo mật
- **Đăng ký tài khoản** với validation đầy đủ
- **Đăng nhập** với xác thực an toàn
- **Ghi nhớ đăng nhập** (Remember me)
- **Quản lý session** bảo mật
- **Đăng xuất** an toàn

### 🎨 Giao diện người dùng
- **Responsive design** - hoạt động tốt trên mọi thiết bị
- **Modern UI/UX** với gradient và animation
- **Glass morphism** design
- **Hover effects** và transitions mượt mà
- **Dark/Light theme** ready

### ⚡ Hiệu suất
- **Tối ưu hóa database** queries
- **Connection pooling** cho hiệu suất cao
- **Caching** thông minh
- **Lazy loading** cho resources

## 🛠️ Công nghệ sử dụng

### Backend
- **Java 24** - Ngôn ngữ lập trình chính
- **Jakarta Servlet 6.1** - Web framework
- **JSP (JavaServer Pages)** - Template engine
- **JSTL** - JSP Standard Tag Library
- **Maven** - Build tool và dependency management

### Database
- **Microsoft SQL Server** - Hệ quản trị cơ sở dữ liệu
- **JDBC** - Database connectivity

### Frontend
- **HTML5** - Markup language
- **CSS3** - Styling với modern features
- **JavaScript** - Client-side interactions
- **Responsive Design** - Mobile-first approach

### Development Tools
- **IntelliJ IDEA** - IDE chính
- **Git** - Version control
- **Maven** - Build automation

## 📦 Cài đặt

### Yêu cầu hệ thống
- Java JDK 24 hoặc cao hơn
- Maven 3.9+ 
- Microsoft SQL Server 2019+
- Apache Tomcat 10+ hoặc GlassFish 7+

### Bước 1: Clone repository
```bash
git clone https://github.com/your-username/trangdangnhap-trunk.git
cd trangdangnhap-trunk
```

### Bước 2: Cấu hình database
1. Tạo database mới trong SQL Server
2. Chạy script SQL để tạo bảng users
3. Cập nhật thông tin kết nối trong `src/main/resources/database.properties`

### Bước 3: Build project
```bash
mvn clean install
```

## 🚀 Chạy ứng dụng

### Sử dụng Maven
```bash
mvn tomcat7:run
```

### Sử dụng IDE
1. Import project vào IntelliJ IDEA
2. Cấu hình Tomcat server
3. Deploy và run

### Truy cập ứng dụng
Mở trình duyệt và truy cập: `http://localhost:8080/trangdangnhap`

## 📁 Cấu trúc dự án

```
trangdangnhap-trunk/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/trangdangnhap/
│   │   │       ├── controller/     # Servlet controllers
│   │   │       ├── dao/           # Data Access Objects
│   │   │       ├── model/         # Entity classes
│   │   │       ├── service/       # Business logic
│   │   │       └── util/          # Utility classes
│   │   ├── resources/             # Configuration files
│   │   └── webapp/
│   │       ├── Views/             # JSP pages
│   │       │   ├── login.jsp      # Login page
│   │       │   ├── register.jsp   # Registration page
│   │       │   ├── home.jsp       # Dashboard
│   │       │   └── dangnhap.jsp   # Alternative login
│   │       ├── WEB-INF/           # Web configuration
│   │       └── index.jsp          # Landing page
│   └── test/                      # Unit tests
├── pom.xml                        # Maven configuration
└── README.md                      # Project documentation
```

## 🎨 Giao diện

### Trang chủ (Landing Page)
- Hero section với animation
- Feature highlights
- Call-to-action buttons
- Responsive design

### Trang đăng nhập
- Modern card design
- Form validation
- Remember me functionality
- Error/success messages

### Trang đăng ký
- User-friendly form
- Password requirements
- Real-time validation
- Smooth transitions

### Dashboard
- User information display
- Navigation bar
- Feature cards
- Logout functionality

## 🔧 Cấu hình

### Database Configuration
Tạo file `src/main/resources/database.properties`:
```properties
db.url=jdbc:sqlserver://localhost:1433;databaseName=your_database
db.username=your_username
db.password=your_password
db.driver=com.microsoft.sqlserver.jdbc.SQLServerDriver
```

### Web Configuration
Cấu hình trong `src/main/webapp/WEB-INF/web.xml`:
```xml
<servlet>
    <servlet-name>UserController</servlet-name>
    <servlet-class>org.example.trangdangnhap.UserController</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>UserController</servlet-name>
    <url-pattern>/login</url-pattern>
    <url-pattern>/register</url-pattern>
    <url-pattern>/logout</url-pattern>
</servlet-mapping>
```

## 📝 API Endpoints

| Method | Endpoint | Mô tả |
|--------|----------|-------|
| `GET` | `/` | Landing page |
| `GET` | `/login` | Hiển thị form đăng nhập |
| `POST` | `/login` | Xử lý đăng nhập |
| `GET` | `/register` | Hiển thị form đăng ký |
| `POST` | `/register` | Xử lý đăng ký |
| `GET` | `/home` | Dashboard (yêu cầu đăng nhập) |
| `GET` | `/logout` | Đăng xuất |

## 🤝 Đóng góp

Chúng tôi rất hoan nghênh mọi đóng góp! Hãy làm theo các bước sau:

1. **Fork** dự án
2. Tạo **feature branch** (`git checkout -b feature/AmazingFeature`)
3. **Commit** thay đổi (`git commit -m 'Add some AmazingFeature'`)
4. **Push** lên branch (`git push origin feature/AmazingFeature`)
5. Tạo **Pull Request**

### Guidelines
- Tuân thủ coding standards
- Viết unit tests cho tính năng mới
- Cập nhật documentation
- Kiểm tra build thành công

## 📄 Giấy phép

Dự án này được phân phối dưới giấy phép MIT. Xem file `LICENSE` để biết thêm chi tiết.

## 👥 Tác giả

**Tên của bạn** - [GitHub](https://github.com/your-username)

## 🙏 Lời cảm ơn

- [Jakarta EE](https://jakarta.ee/) - Enterprise Java platform
- [Maven](https://maven.apache.org/) - Build tool
- [SQL Server](https://www.microsoft.com/en-us/sql-server/) - Database
- [Bootstrap](https://getbootstrap.com/) - CSS framework inspiration

---

⭐ Nếu dự án này hữu ích, hãy cho chúng tôi một star trên GitHub!
