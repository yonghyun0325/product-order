# Product Order

Spring Boot + JPA + PostgreSQL 기반의 상품 및 주문 관리 프로젝트입니다.

---

# 기술 스택

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Docker
* Gradle

---

# 실행 방법

## PostgreSQL 실행

```bash
docker compose up -d
```

## 애플리케이션 실행

```bash
./gradlew bootRun
```

---

# 구현 기능

## 상품 관리

### 상품 등록

```http
POST /products
```

### 상품 단건 조회

```http
GET /products/{id}
```

### 상품 목록 조회

```http
GET /products
```

### 상품 수정

```http
PUT /products/{id}
```

### 상품 삭제

```http
DELETE /products/{id}
```

---

## 주문 관리

### 주문 생성

```http
POST /orders
```

### 주문 단건 조회

```http
GET /orders/{id}
```

### 주문 목록 조회

```http
GET /orders?page=0&size=10
```

### 주문 수정

```http
PUT /orders/{id}
```

### 주문 삭제

```http
DELETE /orders/{id}
```

---

# 주문 조회

주문 조회 시 상품명이 함께 노출되도록 구현하였습니다.

응답 예시

```json
{
  "orderId": 1,
  "productId": 2,
  "productName": "맥북 프로 M4",
  "quantity": 2
}
```

---

# 페이지네이션

주문 목록 조회 시 페이지네이션을 적용하였습니다.

요청 예시

```http
GET /orders?page=0&size=10
```

응답 예시

```json
{
  "content": [
    {
      "orderId": 1,
      "productId": 5,
      "productName": "테스트 상품",
      "quantity": 1
    }
  ],
  "totalElements": 2,
  "totalPages": 1,
  "size": 10,
  "number": 0
}
```

### 실행 결과

> Postman 실행 결과 캡처 첨부

---

# N+1 문제 방지

주문 목록 조회 시 Product 엔티티를 함께 조회하기 위해 `@EntityGraph`를 적용하였습니다.

```java
@EntityGraph(attributePaths = "product")
Page<ProductOrder> findAll(Pageable pageable);
```

설명

주문 목록 조회 시 Product 정보를 함께 조회하기 위해 `@EntityGraph(attributePaths = "product")`를 적용하였습니다.

이를 통해 주문 조회 시 Product를 추가 조회하지 않고 함께 조회하여 N+1 문제를 방지하였습니다.

---

# 재고 관리

상품에 stock 필드를 추가하였습니다.

## 재고 차감 테스트

재고가 1인 상품을 생성한 후 동일한 주문을 2회 요청하여 재고 차감 동작을 확인하였습니다.

### 첫 번째 주문

요청

```json
{
  "productId": 5,
  "quantity": 1
}
```

결과

```text
200 OK
주문 성공
```

### 두 번째 주문

요청

```json
{
  "productId": 5,
  "quantity": 1
}
```

결과

```json
{
  "message": "재고가 부족합니다."
}
```

```text
400 Bad Request
```

### 실행 결과

> Postman 실행 결과 캡처 첨부

---

## 재고 부족 처리

재고보다 많은 수량을 주문하면 주문 생성이 실패합니다.

응답 예시

```json
{
  "message": "재고가 부족합니다."
}
```

---

# 예외 처리

전역 예외 처리(Global Exception Handler)를 적용하였습니다.

응답 예시

```json
{
  "message": "상품을 찾을 수 없습니다. id=999"
}
```

---

# 트랜잭션

주문 생성과 재고 차감은 하나의 트랜잭션으로 처리합니다.

```java
@Transactional
```

원자성(Atomic) 보장을 위해 주문 생성과 재고 차감을 하나의 트랜잭션으로 처리하였습니다.

주문 생성 중 예외가 발생할 경우 재고 차감도 함께 롤백되어 데이터 정합성을 유지합니다.

다만 여러 사용자가 동시에 주문하는 환경에서는 동시성 문제가 발생할 수 있으므로, 실무에서는 다음과 같은 방법을 적용할 수 있습니다.

* Pessimistic Lock
* Optimistic Lock
* Redis 분산 락

이번 과제에서는 구현 복잡도와 요구사항을 고려하여 `@Transactional` 방식을 선택하였습니다.

---

# 프로젝트 구조

```text
src/main/java/com/example/productorder

├── global.exception
├── order
│   ├── dto
│   ├── OrderController
│   ├── OrderRepository
│   ├── OrderService
│   └── ProductOrder
│
├── product
│   ├── dto
│   ├── ProductController
│   ├── ProductRepository
│   ├── ProductService
│   ├── StockService
│   └── Product
│
└── ProductOrderApplication
```
