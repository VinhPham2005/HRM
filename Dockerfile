# --- Bước 1: Build ứng dụng ---
# Sử dụng Maven để tải thư viện và build ra file .jar
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Copy file pom.xml và thư mục src vào trong container
COPY pom.xml .
COPY src ./src
# Chạy lệnh build của Maven, bỏ qua test để build nhanh hơn
RUN mvn clean package -DskipTests

# --- Bước 2: Chạy ứng dụng ---
# Sử dụng môi trường Java nhẹ (Alpine) để chạy file .jar nhằm giảm dung lượng
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copy file .jar đã build từ Bước 1 sang Bước 2
COPY --from=build /app/target/*.jar app.jar

# Mở port 8080 (port mặc định của Spring Boot)
EXPOSE 8080

# Lệnh khởi chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]