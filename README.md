# Product Order

Spring Boot와 JPA를 활용한 상품 및 주문 관리 API 프로젝트입니다.

## 기술 스택

- Java 21
- Spring Boot 3.5
- Spring Data JPA
- PostgreSQL 16
- Docker
- Gradle
- Git / GitHub

---

## 프로젝트 구조

```text
com.example.productorder

├── product
│   ├── Product
│   ├── ProductController
│   ├── ProductService
│   ├── ProductRepository
│   └── dto
│
├── order
│   ├── ProductOrder
│   ├── OrderController
│   ├── OrderService
│   ├── OrderRepository
│   └── dto
│
└── global
    └── exception
```

---

## 실행 방법

### PostgreSQL 실행

```bash
docker compose up -d
```

### 애플리케이션 실행

```bash
./gradlew bootRun
```

또는 IntelliJ에서

```text
ProductOrderApplication 실행
```

---

## API

### Product

#### 상품 등록

```http
POST /products
```

Request

```json
{
  "name": "맥북 프로",
  "price": 3500000
}
```

---

#### 상품 목록 조회

```http
GET /products
```

---

#### 상품 단건 조회

```http
GET /products/{id}
```

---

#### 상품 수정

```http
PUT /products/{id}
```

Request

```json
{
  "name": "맥북 프로 M4",
  "price": 4000000
}
```

---

#### 상품 삭제

```http
DELETE /products/{id}
```

---

## Order

### 주문 생성

```http
POST /orders
```

Request

```json
{
  "productId": 1,
  "quantity": 2
}
```

---

### 주문 목록 조회

```http
GET /orders
```

---

### 주문 단건 조회

```http
GET /orders/{id}
```

---

### 주문 수정

```http
PUT /orders/{id}
```

Request

```json
{
  "quantity": 5
}
```

---

### 주문 삭제

```http
DELETE /orders/{id}
```

---

## 예외 처리

전역 예외 처리(`@RestControllerAdvice`)를 적용했습니다.

예시:

```json
{
  "message": "상품을 찾을 수 없습니다. id=999"
}
```

---

## 데이터베이스

### Product

| 컬럼 | 타입 |
|--------|--------|
| id | bigint |
| name | varchar |
| price | numeric |

### ProductOrder

| 컬럼 | 타입 |
|--------|--------|
| id | bigint |
| product_id | bigint |
| quantity | integer |

---

## Git Flow

- main
- feature/order
- feature/order-list

브랜치 전략을 사용하여 기능 단위로 개발 후 Pull Request를 통해 병합했습니다.

---

## 구현 기능

### Product

- 상품 등록
- 상품 목록 조회
- 상품 단건 조회
- 상품 수정
- 상품 삭제

### Order

- 주문 생성
- 주문 목록 조회
- 주문 단건 조회
- 주문 수정
- 주문 삭제

### 공통

- PostgreSQL
- Docker
- Spring Data JPA
- 전역 예외 처리
- GitHub Pull Request Workflow
