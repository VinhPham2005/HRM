# Nghiên cứu tuần 4:

## JWT

**JSON là viết tắt của `JavaScript Object Notation`, là một kiểu định dạng dữ liệu tuân theo một quy luật nhất định mà hầu hết các ngôn ngữ lập trình hiện nay đều có thể đọc được. JSON là một tiêu chuẩn mở để trao đổi dữ liệu trên web.**

**JSON sử dụng các cặp `key` - `value` đễ dữ liệu sử dụng.**

## Định nghĩa

**JWT (JSON Web Token) là một tiêu chuẩn mở định dạng token dưới dạng JSON để truyền tải thông tin giữa các bên một cách an toàn.**

**Thường dùng để xác thực và trao đổi thông tin trong các ứng dụng web. Gồm 3 phần: Header, Payload và Signature, được mã hóa và nối với nhau bằng 3 dấu (.)**

### Cấu trúc chi tiết

#### Header

**Chứa thông tin về loại token và thuật toán mã hóa được sử dụng.**

vd:

{
"alg": "HS256",
"typ": "JWT"
}

#### Payload

**Chứa các claims (yêu cầu) - là những thông tin được mã hóa trong token. có thể là id, email, thời gian hết hạn.

vd:


{
"sub": "1234567890",
"name": "John Doe",
"admin": true
}

#### Signature

**Để xác thực token**.

**Được tạo ra bằng kết hợp mã hóa Header và Payload với 1 khóa bí mật.**

### Cách hoạt động

1. Tạo JWT: Khi người dùng đăng nhập, server sẽ xác thực thông tin đăng nhập và tạo một JWT chứa các thông tin cần thiết. Sau này được gửi lại cho người dùng.

2. Gửi JWT: server sẽ gửi token này về phía client, lưu trữ trong localstorage hoặc Cookie. Khi người dùng thực hiện các yêu cầu khác thì JWT sẽ được gửi kèm trong header của yêu cầu HTTP (thường là giá trị "Bearer <token>".

3. Xác thực JWT: Khi nhận yêu cầu từ client kèm với JWT thì server sẽ thực hiện các bước sau:
    - Tách JWT: tách thành 3 phần: Header, Payload, Signature.
    - Giải mã Header và Payload để học các dữ liệu trong đó.
    - Xác thực Signature: đảm bảo token không bị giả mạo
    - Kiểm tra các claims trong Payload: đảm bảo token còn thời hạn và người dùng có quyền để truy cập.

### Ưu nhược điểm

#### Ưu điểm

1. Bảo mật cao: sử dụng chữ ký số mạnh HMAC, SHA256

2. Stateless: tất cả thông tin được lưu trong token thay vì trên server -> tăng hiệu suất.

3. Tính di động cao: dễ dàng truyền tải giữa các hệ thống và dịch vụ khác nhau.

4. Dễ dàng sử dụng: sử dụng định dạng JSON dễ đọc, tạo, phân tích.

5. Tích hợp với nhiều hệ thống hiện đại: phù hợp với OAuth 2.0 và các dịch vụ RESTful API.

#### Nhược điểm

1. Rủi ro bảo mật: nguy hiểm nếu mất token.

2. Kích thước lớn: JWT có thể khá lớn, chứa nhiều thông tin.

3. Cơ chế làm mới token: JWT có thời gian hoạt động cố định.

## Spring security

### Các khái niệm

**Là một framework bảo mật được phát triển bởi SpringSource**.

**Cung cấp tính năng xác thực (Authentication) và phân quyền (Authorization).**

**Cung cấp các giao thức bảo mật: HTTPS, OAuth 2.0, JWT, ...**

### Cơ chế hoạt động

**Hoạt động theo mô hình client-Server**

**Khi yêu cầu được gửi đến ứng dụng web, sẽ được chuyển qua một chuỗi các bộ lọc (filter chain) -> xác thực, kiểm tra phân quyền, ...**

**Nếu không thỏa mãn -> Spring Security sẽ sinh ra một event đẻ thông báo cho ứng dụng biết. -> xử lý  các sự kiện theo ý muốn (ghi log, gửi email, hiện thông báo)**

**Nếu thỏa mãn -> được xử lý như bình thường -> các phản hồi cũng đi qua lại các chuỗi bộ lọc**

### Các thành phần cơ bản

#### Authentication

- Xác thực xem người dùng có quyền truy cập vào ứng dụng hay không.

- Dựa trên thông tin nhận dạng (identifier), thông tin bí mật (credential) (tên đăng nhập, mật khẩu, token, vân tay, ...

- Xác thực thông qua một số cơ chế:
    + Form-based authentication: thông qua form đăng nhập
    + HTTP basic au: các header authorization
    + Custom login page: trang đang nhập tùy chỉnh
    + Pre-authenticated au: thông qua các giá trị được cung cấp từ máy khách

- Hỗ trợ cả **trạng thái** và **phi trạng thái**

    + Trạng thái: lưu thông tin xác thực vào một phiên (session) trên máy chủ. Kiểm tra phiên hiện tại để xác thực.

    + Phi trạng thái: không lưu thông tin xác thực trên máy chủ mà sử dụng token được ký số để xác thực thông tin.

#### Authorization

- Xác thực quyền hạn khi truy cập vào ứng dụng

- Dựa trên thông tin về vai trò, nhóm, quyền hạn, chính sách.

### Ưu nhược điểm

#### Ưu điểm

- Mạnh mẽ và linh hoạt, hỗ trợ nhiều tiêu chuẩn và giao thức bảo mật

- Tích hợp sẵn với Spring

- Cộng đồng lớn, nhiều tài liệu

#### Nhược điểm

- Cấu hình phức tạp, khó hiểu

- Không phù hợp với một số loại ứng dụng web vd web không sử dụng Spring

- Yêu cầu kiến thức chuyên môn về bảo mật

## Servlet

**Để tạo ra ứng dụng web(nằm ở phía máy chủ và tạo ra trang web động)**

**Thu thập thông tin đầu vào từ người dùng, hiển thị bản ghi từ csdl**

**Có nhiều interface và các lớp API servlet như Servlet, GenericServlet, HttpServlet, ServletRequest, ServletResponse, ...**

### Nhiệm vụ

- Đọc dữ liệu do khách hàng gửi

- Đọc yêu cầu HTTP ẩn được gửi bởi client(cookie, ...)

- Gửi dữ liệu rõ ràng (HTML, XML, GIF, Excel, ...)	
