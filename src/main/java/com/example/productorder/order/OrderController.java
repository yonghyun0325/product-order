package com.example.productorder.order;

import com.example.productorder.order.dto.OrderCreateRequest;
import com.example.productorder.order.dto.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * 주문 생성 API
     *
     * POST /orders
     *
     * @param request 주문 생성 요청 정보
     * @return 생성된 주문 정보
     */
    @PostMapping
    public OrderResponse create(@RequestBody @Valid OrderCreateRequest request) {
        return orderService.create(request);
    }
    /**
     * 주문 단건 조회 API
     *
     * GET /orders/{id}
     */
    @GetMapping("/{id}")
    public OrderResponse getById(
            @PathVariable Long id
    ) {
        return orderService.getById(id);
    }
}