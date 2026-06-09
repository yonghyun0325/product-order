package com.example.productorder.product;

import com.example.productorder.product.dto.ProductCreateRequest;
import com.example.productorder.product.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.productorder.product.dto.ProductUpdateRequest;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse create(ProductCreateRequest request) {

        Product product = new Product(
                request.name(),
                request.price(),
                request.stock()
        );

        Product savedProduct = productRepository.save(product);

        return new ProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getStock()
        );
    }

    /**
     * 상품 ID로 상품을 조회한다.
     *
     * @param id 조회할 상품 ID
     * @return 조회된 상품 정보
     * @throws IllegalArgumentException 상품이 존재하지 않을 경우
     */
    public ProductResponse getById(Long id) {

        // 상품 ID로 조회
        Product product = productRepository.findById(id)

                // 상품이 없으면 예외 발생
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "상품을 찾을 수 없습니다. id=" + id));

        // Entity -> Response DTO 변환
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
    /**
     * 전체 상품 목록을 조회한다.
     *
     * @return 상품 응답 목록
     */
    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getStock()
                ))
                .toList();
    }
    /**
     * 상품 정보를 수정한다.
     *
     * @param id 수정할 상품 ID
     * @param request 상품 수정 요청 정보
     * @return 수정된 상품 정보
     */
    public ProductResponse update(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id=" + id));

        product.update(request.name(), request.price(), request.stock());

        Product savedProduct = productRepository.save(product);

        return new ProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getStock()
        );
    }
    /**
     * 상품을 삭제한다.
     *
     * @param id 삭제할 상품 ID
     */
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id=" + id));

        productRepository.delete(product);
    }
}