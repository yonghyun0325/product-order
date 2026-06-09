package com.example.productorder.order;

import com.example.productorder.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주문 엔티티
 *
 * 하나의 주문은 하나의 상품을 참조한다.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOrder {

    /**
     * 주문 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 주문 상품
     *
     * ManyToOne:
     * 여러 주문이 하나의 상품을 참조할 수 있다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * 주문 수량
     */
    @Column(nullable = false)
    private Integer quantity;

    public ProductOrder(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    /**
     * 주문 수량 수정
     *
     * @param quantity 변경할 수량
     */
    public void updateQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}