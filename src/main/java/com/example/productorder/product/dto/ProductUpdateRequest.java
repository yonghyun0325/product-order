package com.example.productorder.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * 상품 수정 요청 DTO
 */
public record ProductUpdateRequest(

        @NotBlank
        String name,

        @NotNull
        @DecimalMin("0.0")
        BigDecimal price

) {
}