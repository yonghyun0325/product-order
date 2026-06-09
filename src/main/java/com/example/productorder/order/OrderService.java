package com.example.productorder.order;

import com.example.productorder.order.dto.OrderCreateRequest;
import com.example.productorder.order.dto.OrderResponse;
import com.example.productorder.product.Product;
import com.example.productorder.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.productorder.order.dto.OrderUpdateRequest;

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
    /**
     * 전체 주문 목록을 조회한다.
     *
     * @return 주문 응답 목록
     */
    public List<OrderResponse> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(order -> new OrderResponse(
                        order.getId(),
                        order.getProduct().getId(),
                        order.getProduct().getName(),
                        order.getQuantity()
                ))
                .toList();
    }
    /**
     * 주문 수량을 수정한다.
     *
     * @param id 수정할 주문 ID
     * @param request 주문 수정 요청 정보
     * @return 수정된 주문 정보
     */
    public OrderResponse update(Long id, OrderUpdateRequest request) {
        ProductOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다. id=" + id));

        order.updateQuantity(request.quantity());

        ProductOrder savedOrder = orderRepository.save(order);

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getProduct().getId(),
                savedOrder.getProduct().getName(),
                savedOrder.getQuantity()
        );
    }
    /**
     * 주문을 삭제한다.
     *
     * @param id 삭제할 주문 ID
     */
    public void delete(Long id) {
        ProductOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다. id=" + id));

        orderRepository.delete(order);
    }
}