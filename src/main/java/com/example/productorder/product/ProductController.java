package com.example.productorder.product;

import com.example.productorder.product.dto.ProductCreateRequest;
import com.example.productorder.product.dto.ProductResponse;
import com.example.productorder.product.dto.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 등록 API
     *
     * POST /products
     *
     * @param request 상품 등록 요청 정보
     * @return 등록된 상품 정보
     */
    @PostMapping
    public ProductResponse create(@RequestBody ProductCreateRequest request) {
        return productService.create(request);
    }
    /**
     * 상품 단건 조회 API
     *
     * GET /products/{id}
     *
     * @param id 조회할 상품 ID
     * @return 상품 정보
     */
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.getById(id);
    }
    /**
     * 상품 목록 조회 API
     *
     * GET /products
     *
     * @return 전체 상품 목록
     */
    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }
    /**
     * 상품 수정 API
     *
     * PUT /products/{id}
     *
     * @param id 수정할 상품 ID
     * @param request 상품 수정 요청 정보
     * @return 수정된 상품 정보
     */
    @PutMapping("/{id}")
    public ProductResponse update(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequest request
    ) {
        return productService.update(id, request);
    }
    /**
     * 상품 삭제 API
     *
     * DELETE /products/{id}
     *
     * @param id 삭제할 상품 ID
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}