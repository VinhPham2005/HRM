# Thực tập Cơ sở – Kế hoạch & Tiến độ 08 Tuần

---

## 1. Thông tin sinh viên

- **Họ tên:** Phạm Quang Vinh  
- **MSSV:** B23DCCE100  
- **Lớp:** D23CQCE04-B  
- **Email:** VinhPQ.B23CE100@stu.ptit.edu.vn  
- **GitHub username:** VinhPham2005  
- **Link repository:** (sẽ cập nhật sau khi hoàn thiện project)

---

## 2. Định hướng & Mục tiêu

> Trả lời 3 câu hỏi này trước khi xác định kỹ năng:

### Tôi muốn làm vai trò gì sau khi ra trường?

→ Backend Developer sử dụng Spring Boot

---

### Kỹ năng tôi còn thiếu / yếu trong vai trò đó là gì?

Hiện tại tôi còn thiếu kinh nghiệm trong:

- Thiết kế RESTful API
- Xây dựng backend với Spring Boot
- Làm việc với database thông qua Spring Data JPA
- Xây dựng hệ thống xác thực và phân quyền với Spring Security
- Container hóa ứng dụng bằng Docker

---

### Tôi có thể học và thực hành được kỹ năng đó trong 8 tuần không?

→ Có.

Trong 8 tuần có thể xây dựng một dự án backend nhỏ nhưng đầy đủ chức năng, qua đó giúp:

- Hiểu quy trình xây dựng REST API
- Thực hành thiết kế database
- Làm quen với các công nghệ backend phổ biến

---

## 3. Đăng ký chủ đề thực tập

### Định hướng

- [ ] Cải thiện / củng cố kỹ năng đang có (7–8)  
- [x] Học kỹ năng mới / theo trend (8–9)  
- [ ] Gắn với nghiên cứu / đề tài / dự án (9–10)

---

### 1–3 kỹ năng chính muốn học / cải thiện

1. Backend với Spring Boot và Spring Data JPA  
2. RESTful API Design  
3. Authentication & Authorization với Spring Security  
4. Docker cơ bản  

---

### Bài toán / nội dung áp dụng cụ thể

Xây dựng hệ thống **Quản lý Nhân sự công ty** dưới dạng RESTful API backend.

Hệ thống bao gồm các chức năng:

- Quản lý thông tin nhân viên  
- Quản lý phòng ban  
- Phân quyền người dùng (Admin / Manager / User)  
- Dashboard thống kê dữ liệu  
- Audit theo dõi thay đổi dữ liệu  
- Export báo cáo  
- Docker hóa hệ thống  

Công nghệ sử dụng:

- Spring Boot  
- Spring Data JPA  
- Spring Security  
- PostgreSQL  
- Docker  

---

### Làm việc nhóm (nếu có)

Cá nhân

---

## 4. Kế hoạch thực hiện 08 tuần

> **Nguyên tắc:** Đọc → Hiểu → Thực hiện. Không bỏ qua bước nào.
>
> Mỗi tuần gồm **2 phần**:
> - **Tìm hiểu / Đọc:** Tài liệu, khái niệm, ví dụ tham khảo
> - **Thực hành:** Làm thử, ghi lại kết quả
>
> Kết quả đầu ra: file `.md` ghi chú, link code, screenshot, hoặc demo URL

---

### Tuần 1 – Khởi động & Tìm hiểu nền tảng

_Mục tiêu tuần: Nắm vững khái niệm cốt lõi trước khi thực hành_

#### Tìm hiểu / Đọc

| Nội dung cần đọc / tìm hiểu | Nguồn tham khảo | Trạng thái | Ghi chú / Điều đã hiểu |
|---|---|------------|---|
| Tổng quan Spring Boot và kiến trúc REST API | Spring Documentation | [V]        | Hiểu cách Spring Boot hoạt động |
| Thiết kế cơ sở dữ liệu cho hệ thống quản lý nhân sự | Database Design | [V]        | Hoàn thành sơ đồ ERD |

#### Thực hành

| Nhiệm vụ | Kết quả đầu ra | Trạng thái | Ghi chú                                                                         |
|---|---|------------|---------------------------------------------------------------------------------|
| Phân tích yêu cầu hệ thống | Document phân tích | [V]        | https://docs.google.com/document/d/1I82f0_x5cdZMT5wTc0-fyDirmVfndJ4P-tpGd5d4uU0 |
| Thiết kế CSDL (ERD) | Sơ đồ ERD | [V]        | https://drive.google.com/file/d/1k5OQ12uU863w7sTsySY9kYqGHhuB2EFI               |
| Cấu hình PostgreSQL | Kết nối DB thành công | [ V        | Đã cấu hình thành công.                                                         |

---

### Tuần 2 – Triển khai cơ bản _(Buổi trao đổi 1)_

_Mục tiêu tuần: Áp dụng kiến thức tuần 1 vào thực tế_

#### Tìm hiểu / Đọc

| Nội dung        | Nguồn                                                                                  | Trạng thái | Ghi chú |
|-----------------|----------------------------------------------------------------------------------------|------------|---|
| Spring Data JPA | https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#reference | [V]        | |
| Spring Data JPA | https://docs.spring.io/spring-data/data-jpa/docs/1.4.x/reference/htmlsingle/           | [V]        | |
| Java Entity     | https://www.baeldung.com/jpa-entities                                                  | [V]        | |
| Validation      | https://www.baeldung.com/spring-boot-bean-validation                                   | [V]        | |

#### Thực hành

| Nhiệm vụ                                                | Kết quả đầu ra | Trạng thái | Ghi chú |
|---------------------------------------------------------|---|------------|---|
| Xây dựng Entity (Employee, Department, EmployeeAccount) | Code entity | [V]        | |
| CRUD Employee                                           | API test Postman | [V]        | |
| CRUD Department                                         | API test Postman | [V]        | |
| Validation dữ liệu                                      | `@Valid` hoạt động | [V]        | |

---

### Tuần 3 – Mở rộng & Đào sâu

#### Tìm hiểu / Đọc

| Nội dung | Nguồn | Trạng thái | Ghi chú |
|--|--|------------|---|
|Criteria API|https://kungfutech.edu.vn/bai-viet/spring-boot/su-dung-criterial-api-trong-hibernate| [V]        | |

### Thực hành

| Nhiệm vụ | Kết quả đầu ra | Trạng thái | Ghi chú |
|---|---|------------|---|
| Tìm kiếm nhiều điều kiện (dynamic search) | API search | [V]        | |
| Join bảng | Query hoạt động | [V]        | |
| Phân trang & sắp xếp | Pageable API | [V]        | |

---

### Tuần 4 – Hoàn thiện giữa kỳ _(Buổi trao đổi 2)_

#### Tìm hiểu / Đọc

| Nội dung               | Nguồn | Trạng thái | Ghi chú |
|------------------------|-|------------|---|
| Spring security và JWT |https://viblo.asia/p/lam-sao-bao-mat-ung-dung-spring-voi-spring-security-va-jwt-yZjJYKXbVOE#_7-truy-cap-tai-nguyen-thong-qua-jwt-21| [V]        | |
| JWT                    |https://dangth.dev/post/2023-10-27-jwt-springboot| [V]        | |

### Thực hành 

| Nhiệm vụ                  | Kết quả đầu ra          | Trạng thái | Ghi chú                   |
|---------------------------|-------------------------|------------|---------------------------|
| Tích hợp JWT              | Login API               | [V]        | Hệ thống tích hợp JWT     |
| Phân quyền theo role      | ADMIN / MANAGER / STAFF | [V]        | Có xác thực và phân quyền |
| Global Exception Handling | `@ControllerAdvice`     | [V]        | Có xử lý ngoại lệ         |

---

### Tuần 5 – Nâng cao / Tối ưu

#### Tìm hiểu / Đọc

| Nội dung | Nguồn | Trạng thái | Ghi chú |
|---|---|---|---|
| | | [ ] | |
| | | [ ] | |

### Thực hành 

| Nhiệm vụ | Kết quả đầu ra | Trạng thái | Ghi chú         |
|---|---|------------|-----------------|
| Xây dựng Dashboard API | `/dashboard` API | [V]        | Đã xây dựng api |
| Thống kê theo phòng ban | SQL `GROUP BY` | [V]        |                 |
| Thống kê lương trung bình | `AVG(salary)` | [V]        |                 |
| Audit createdAt / updatedAt | Tracking hoạt động | [V]        |                 |

---

### Tuần 6 – Kiểm thử & Đánh giá _(Buổi trao đổi 3)_

#### Tìm hiểu / Đọc

| Nội dung | Nguồn                                 | Trạng thái | Ghi chú |
|----------|---------------------------------------|------------|---|
| Docker   | https://topdev.vn/blog/docker-la-gi/  | [V]        | |
|          |                                       | [ ]        | |

### Thực hành 

| Nhiệm vụ | Kết quả đầu ra | Trạng thái | Ghi chú                                 |
|---|---|------------|-----------------------------------------|
| Test API toàn hệ thống | Postman collection | [V]        |                                         |
| Dockerize project | Dockerfile | [V]        | Đã tạo Dockerfile và docker-compose.yml |
| Deploy local bằng Docker | Container chạy OK | [ ]        | Chưa thành công                         |

---

### Tuần 7 – Hoàn thiện cuối

**Thực hành:**

| Nhiệm vụ | Kết quả đầu ra | Trạng thái | Ghi chú |
|---|---|---|---|
| Hoàn thiện `docs/` (ghi chú học tập) | | [ ] | |
| Hoàn thiện `src/project/` | | [ ] | |
| Viết README tổng kết | | [ ] | |

---

### Tuần 8 – Tổng kết _(Buổi trao đổi 4)_

| Nhiệm vụ | Kết quả đầu ra | Trạng thái | Ghi chú |
|---|---|---|---|
| Demo hệ thống | Demo URL / Report | [ ] | |
| Hoàn thiện báo cáo cuối kỳ | File PDF | [ ] | |
| Tổng kết & tự đánh giá | Báo cáo hoàn chỉnh | [ ] | |

---

## 5. Checklist & Tổng kết

- [ ] Nộp kế hoạch trước **23/02/2026**
- [ ] Tham gia đủ **04 buổi trao đổi**
- [ ] Cập nhật `timeline-08tuan.md` mỗi tuần
- [ ] Có ghi chú tìm hiểu trong `docs/`
- [ ] Sản phẩm cuối chạy được / demo được
- [ ] Có thể **giải thích** những gì đã làm mà không cần nhìn code

---

### Tự đánh giá mức độ hoàn thành

…… %

---

### Vướng mắc / nội dung cần giảng viên hỗ trợ thêm

(Điền trong quá trình thực hiện)

---

### Link repository cuối cùng

(Sẽ cập nhật sau)