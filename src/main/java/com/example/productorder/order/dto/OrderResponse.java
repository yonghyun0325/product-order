package com.example.productorder.order.dto;

/**
 * 주문 응답 DTO
 */
public record OrderResponse(
        Long orderId,
        Long productId,
        String productName,
        Integer quantity
) {
}