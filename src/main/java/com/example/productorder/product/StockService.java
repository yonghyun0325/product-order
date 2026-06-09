package com.example.productorder.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 재고 관리 서비스
 */
@Service
@RequiredArgsConstructor
public class StockService {

    public void decrease(Product product, Integer quantity) {

        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        product.updateStock(product.getStock() - quantity);
    }
}