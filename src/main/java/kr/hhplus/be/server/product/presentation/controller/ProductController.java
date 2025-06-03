package kr.hhplus.be.server.product.presentation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kr.hhplus.be.server.global.dto.ApiResult;
import kr.hhplus.be.server.product.application.mapper.ProductMapper;
import kr.hhplus.be.server.product.application.port.in.ProductUseCase;
import kr.hhplus.be.server.product.presentation.dto.request.ProductOptionRequest;
import kr.hhplus.be.server.product.presentation.dto.request.ProductRequest;
import kr.hhplus.be.server.product.presentation.dto.response.ProductOptionResponse;
import kr.hhplus.be.server.product.presentation.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;

    @GetMapping
    public ResponseEntity<ApiResult<List<ProductResponse>>> searchProducts(
            @RequestParam(required = false, value = "name") String name,
            @RequestParam(required = false, value = "page", defaultValue = "0") int page,
            @RequestParam(required = false, value = "size", defaultValue = "10") int size
    ) {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResult<ProductResponse>> getProduct(@PathVariable Long productId) {

        ProductResponse response = productUseCase.getProduct(productId);

        return ResponseEntity.ok(
                ApiResult.ok(response)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResult<ProductResponse>> registerProduct(
            @RequestBody @Valid ProductRequest request
    ) {

        ProductResponse response = productUseCase.registerProduct(ProductMapper.toEntity(request));

        return ResponseEntity.ok(
                ApiResult.ok(response)
        );
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResult<Void>> updateProduct(
            @PathVariable Long productId,
            @RequestBody @Valid ProductRequest request
    ) {

        productUseCase.updateProduct(productId, request.name(), request.description(), request.status());

        return ResponseEntity.ok(
                ApiResult.ok()
        );
    }

    @GetMapping("/{productId}/options")
    public ResponseEntity<ApiResult<List<ProductOptionResponse>>> getOptions(
            @PathVariable @Min(1) Long productId
    ) {

        List<ProductOptionResponse> responses = productUseCase.getOptions(productId);

        return ResponseEntity.ok(
                ApiResult.ok(responses)
        );
    }

    @PostMapping("/{productId}/options")
    public ResponseEntity<ApiResult<ProductResponse>> addOptions(
            @PathVariable Long productId,
            @RequestBody @Valid ProductOptionRequest request
    ) {

        ProductResponse response = productUseCase.addOptions(
                productId,
                request.options().stream().map(ProductMapper::toEntity).toList()
        );

        return ResponseEntity.ok(
                ApiResult.ok(response)
        );
    }

    @PutMapping("/{productId}/options/{optionId}")
    public void updateOption(@PathVariable Long productId,
                             @PathVariable Long optionId,
                             @RequestBody UpdateProductOptionRequest request) {
        productService.updateProductOption(productId, optionId, request);
    }

    @GetMapping("/{productId}/options/{optionId}/histories")
    public List<ProductOptionHistoryResponse> getOptionHistories(@PathVariable Long productId,
                                                                 @PathVariable Long optionId) {
        return productService.getOptionHistories(productId, optionId);
    }
}
