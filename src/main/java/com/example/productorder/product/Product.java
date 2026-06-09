package com.example.productorder.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 상품 엔티티
 *
 * 데이터베이스의 product 테이블과 매핑된다.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    /**
     * 상품 고유 ID (PK)
     *
     * GenerationType.IDENTITY:
     * PostgreSQL의 Auto Increment 전략 사용
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품명
     *
     * nullable = false:
     * NOT NULL 제약조건 생성
     */
    @Column(nullable = false)
    private String name;

    /**
     * 상품 가격
     *
     * precision = 15:
     * 전체 자리수
     *
     * scale = 2:
     * 소수점 자리수
     *
     * BigDecimal 사용 이유:
     * 금액 계산 시 float, double 오차 방지
     */
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    /**
     * 상품 생성 생성자
     *
     * @param name 상품명
     * @param price 상품 가격
     */
    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    /**
     * 상품 정보 수정
     *
     * @param name 변경할 상품명
     * @param price 변경할 가격
     */
    public void update(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}