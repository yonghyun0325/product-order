# Product Order

## 기술 스택

- Java 21
- Spring Boot 3.5.6
- Spring Data JPA
- PostgreSQL
- Docker

## 기능

### Product

- 상품 등록
- 상품 단건 조회
- 상품 목록 조회
- 상품 수정
- 상품 삭제

### Order

- 주문 생성
- 주문 단건 조회

## API

### 상품 등록

POST /products

### 상품 조회

GET /products/{id}

### 주문 생성

POST /orders

### 주문 조회

GET /orders/{id}
