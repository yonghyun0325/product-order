package com.example.productorder.product.dto;

import java.math.BigDecimal;

/**
 * 상품 응답 DTO
 *
 * 상품 조회 및 생성 후
 * 클라이언트에게 반환되는 데이터
 */
public record ProductResponse(
        Long id,
        String name,
        BigDecimal price
) {
}