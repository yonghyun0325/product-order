package com.example.productorder.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * 주문 생성 요청 DTO
 */
public record OrderCreateRequest(

        @NotNull
        Long productId,

        @NotNull
        @Min(1)
        Integer quantity

) {
}