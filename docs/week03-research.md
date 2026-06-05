# Nghiên cứu tuần 3:

## 1. Dynamic Query

**Có 2 cách để tạo dynamic query:**

- Sử dụng `@Query` 
- Sử dụng `Criteria API` 

### 1.1 Sử dụng `@Query`

**Ta có thể viết truy vấn với annotation @Query**

Có 2 cách chính để viết truy vấn với @Query:

- Dùng JPQL (java persistence query language): thay vì viết lệnh trên các bảng và cột của db thì ta truy vấn trên các Entity và thuộc tính của chúng.

```java
@Query("SELECT e FROM Employee e WHERE e.salary > :minSalary")
List<Employee> findHighSalary(@Param("minSalary") double minSalary);
```

- Dùng Native SQL: viết một câu lệnh sql thuần và Spring Data JPA sẽ thực thi nó trực tiếp.

### 1.2 Sử dụng Dynamic Query (Criteria API)

**Là một thành phần của JPA, giúp xây dựng các truy vấn động một cách hiệu quả, hỗ trợ việc tạo truy vấn mà không cần viết mã SQL trực tiếp.**

**Criteria API sử dụng các đối tượng Java để xây dựng truy vấn, giúp tránh lỗi cú pháp và dễ dàng bảo trì.**

**Chúng ta cần có một EntityManager để sử dụng Criteria API**

```java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
EntityManager em = emf.createEntityManager();
```

**Các bước để sử dụng Criteria API:**

1. Tạo một CriteriaBuilder từ EntityManager

```java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
EntityManager em = emf.createEntityManager();
```

2. Thêm điều kiện vào truy vấn

```java
List<Predicate> predicates = new ArrayList<>();
if (condition1) {
    predicates.add(cb.equal(root.get("field1"), value1));
}
if (condition2) {
    predicates.add(cb.like(root.get("field2"), "%" + value2 + "%"));
}
cq.where(predicates.toArray(new Predicate[0]));
```
3. Thực thi truy vấn

```java
TypedQuery<MyEntity> query = em.createQuery(cq);
List<MyEntity> results = query.getResultList();
```

## 2. Pagination and Sorting

**Là một interface kế thừa từ `PagingAndSortingRepository`**

**Giúp hiển thị một dữ liệu lớn theo từng phần nhỏ, đồng thời là sắp xếp dữ liệu theo một số tiêu chí.**

**Pageable tiện lợi nhưng có thể gây truy vấn DB hai lần (lấy dữ liệu và tổng số trang). Để tối ưu hiệu năng, có thể dùng Slice hoặc List thay vì Page.**


