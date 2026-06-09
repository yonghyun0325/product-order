package com.example.productorder.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * 상품 등록 요청 DTO
 *
 * 클라이언트가 상품 생성 시 전달하는 데이터
 *
 * Validation
 * - name : 필수값
 * - price : 필수값, 0 이상
 */
public record ProductCreateRequest(

        @NotBlank
        String name,

        @NotNull
        @DecimalMin("0.0")
        BigDecimal price

) {
}