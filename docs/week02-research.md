# Nghiên cứu tuần 2

## 1. Spring data JPA 

- Là một phần của Spring Data, cung cấp một cách tiếp cận dễ dàng để làm việc với cơ sở dữ liệu bằng cách sử dụng JPA (Java Persistence API).

- `Repository` là interface trung tâm của Spring Data. Nó đánh dấu để Spring Data tạo ra một implements tự động cho nó.

- Chuỗi kế thừa trong Spring Data JPA: JpaRepository -> PagingAndSortingRepository -> CrudRepository -> Repository

- Thông thường trong thực tế người ta không dùng trực tiếp Repository, mà dùng các interface kế thừa như:

  + CrudRepository

  + PagingAndSortingRepository

  + JpaRepository

    vì chúng cung cấp sẵn nhiều phương thức thao tác dữ liệu.

```java
public interface PersonRepository extends JpaRepository<User, Long> { }
```
- `Repository<T, ID>` là interface cơ bản trong Spring Data, trong đó `T` là kiểu entity mà repository quản lý và `ID` là kiểu dữ liệu của khóa chính của entity đó.

- Một số các phương thức cơ bản:

| Phương thức | Mô tả |
|---|---|
| `<S extends T> S save(S entity)` | Lưu hoặc cập nhật một thực thể vào cơ sở dữ liệu |
| `Optional<T> findById(ID id)` | Tìm một thực thể theo ID |
| `List<T> findAll()` | Lấy toàn bộ dữ liệu của thực thể |
| `void deleteById(ID id)` | Xóa thực thể theo ID |
| `long count()` | Đếm tổng số bản ghi |

- Ngoài các phương thức mặc định, Spring Data JPA còn hỗ trợ **Derived Query Methods** (truy vấn dựa trên tên phương thức).

Ví dụ:

```java
List<Employee> findByLastName(String lastName);
List<Employee> findByAgeGreaterThan(int age);
```

- Spring Data JPA sẽ phân tích tên phương thức, sau đó sẽ được dịch sang câu JPQL tương ứng, Hibernate sẽ chuyển thành sql để chay trên database.


- Ngoài ra, Spring Data JPA còn hỗ trợ **Custom Queries** (truy vấn tùy chỉnh) bằng cách sử dụng `@Query` annotation.

Ví dụ:

```java
@Query("SELECT e FROM Employee e WHERE e.salary > :salary")
List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") double salary);
```

## 2. JPA Entity

- JPA Entity là một lớp trong Java được định nghĩa để ánh xạ tới một bảng trong cơ sở dữ liệu. Mỗi instance của Entity sẽ tương ứng với một bản ghi trong bảng đó.

- Ta cần sử dụng `@Entity` để đánh dấu 1 lớp là 1 Entity. `@Table` để chỉ định tên bảng trong cơ sở dữ liệu (nếu khác với tên lớp). 

- Bên cạnh đó thì chúng ta cần các annotation khác như `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor` để tự động sinh ra các phương thức getter, setter và constructor. Các annotation để định nghĩa khóa chính: `@Id`, quan hệ: `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`.