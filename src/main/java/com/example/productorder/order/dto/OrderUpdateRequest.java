package com.example.productorder.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * 주문 수정 요청 DTO
 */
public record OrderUpdateRequest(

        @NotNull
        @Min(1)
        Integer quantity

) {
}