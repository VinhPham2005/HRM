# Nghiên cứu tuần 6

# Docker

- Là phần mềm giúp building, deploying và running ứng dụng một cách dễ dàng bằng các containers (nền tảng ảo hóa).

- Docker đóng gói phần mềm thành các container tiêu chuẩn hóa, chứa tất cả các thứ cần thiết để phần mêm hoạt động.

## Container

- Là các gói phần mềm chứa tất cả các thành phần cần thiết của một ứng dụng đảm bảo ứng dụng có thể chạy trên mọi môi trường.

**Một số khái niệm**

- Docker Engine : là thành phần chính của Docker, như một công cụ để đóng gói ứng dụng

- Docker Hub : là một “github for docker images”. Trên DockerHub có hàng ngàn public images được tạo bởi cộng đồng

- Container: là một instance của một image,  có thể create, start, stop, move or delete container dựa trên Docker API hoặc Docker CLI

- Docker Client: là một công cụ giúp người dùng giao tiếp với Docker host

- Docker Daemon: lắng nghe các yêu cầu từ Docker Client để quản lý các đối tượng như Container, Image, Network và Volumes thông qua REST API. Các Docker Daemon cũng giao tiếp với nhau để quản lý các Docker Service

- Dockerfile: là một tập tin bao gồm các chỉ dẫn để build một image

- Docker Volumes: là phần dữ liệu được tạo ra khi container được khởi tạo

- Docker Repository: là tập hợp các Docker Images cùng tên nhưng khác tags

- Docker Compose: là công cụ cho phép run app với nhiều Docker containers 1 cách dễ dàng hơn

- Docker Swarm: để phối hợp triển khai container

- Docker Services: là các containers trong production

## Docker images

- Là mẫu chuẩn chứa tất cả các thành phần cần thiết để chạy ứng dụng. Bao gồm mã nguồn, các thư viện, phụ thuộc và các cầu hình cần thiết.

- Đặc điểm:
    - Chỉ đọc: Không thể thay đổi
    - Tầng: Image được xây dựng nhiều tầng.
    - Môi trường nhất quán

## Dockerfile

- Là file config cho docker để build ra file Image. 