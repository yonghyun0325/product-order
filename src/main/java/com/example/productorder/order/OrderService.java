package com.example.productorder.order;

import com.example.productorder.order.dto.OrderCreateRequest;
import com.example.productorder.order.dto.OrderResponse;
import com.example.productorder.product.Product;
import com.example.productorder.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    /**
     * 주문을 생성한다.
     *
     * @param request 주문 생성 요청 정보
     * @return 생성된 주문 정보
     */
    public OrderResponse create(OrderCreateRequest request) {

        // 요청으로 전달받은 상품 ID로 상품 조회
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "상품을 찾을 수 없습니다. id=" + request.productId()
                ));

        // 주문 엔티티 생성
        ProductOrder productOrder = new ProductOrder(
                product,
                request.quantity()
        );

        // 주문 저장
        ProductOrder savedOrder = orderRepository.save(productOrder);

        // Entity -> Response DTO 변환
        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getProduct().getId(),
                savedOrder.getProduct().getName(),
                savedOrder.getQuantity()
        );
    }
    /**
     * 주문 단건 조회
     *
     * @param orderId 주문 ID
     * @return 주문 정보
     */
    public OrderResponse getById(Long orderId) {

        ProductOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "주문을 찾을 수 없습니다. id=" + orderId
                ));

        return new OrderResponse(
                order.getId(),
                order.getProduct().getId(),
                order.getProduct().getName(),
                order.getQuantity()
        );
    }
}