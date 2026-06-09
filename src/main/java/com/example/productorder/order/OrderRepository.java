package com.example.productorder.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ProductOrder, Long> {

    /**
     * 주문 목록 조회 시 Product를 함께 조회하여 N+1 문제를 방지한다.
     */
    @EntityGraph(attributePaths = "product")
    Page<ProductOrder> findAll(Pageable pageable);
}