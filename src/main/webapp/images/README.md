# Thư mục Images

Thư mục này chứa các hình ảnh và icon cho ứng dụng.

## Cấu trúc:
- `default-icon.png` - Icon mặc định cho category
- `category/` - Thư mục chứa icon của các category

## Lưu ý:
- Đặt icon mặc định `default-icon.png` vào thư mục này
- Các icon category sẽ được lưu trong thư mục `category/`
- Hỗ trợ các định dạng: PNG, JPG, JPEG, GIF

## Cách sử dụng:
- Trong code: `${pageContext.request.contextPath}/images/default-icon.png`
- Trong database: lưu đường dẫn tương đối như `category/icon-name.png`
