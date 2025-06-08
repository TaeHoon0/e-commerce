package kr.hhplus.be.server.product.presentation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kr.hhplus.be.server.global.dto.ApiResult;
import kr.hhplus.be.server.product.application.dto.reseponse.ProductResult;
import kr.hhplus.be.server.product.application.port.in.ProductPort;
import kr.hhplus.be.server.product.presentation.dto.request.AddProductOptionRequest;
import kr.hhplus.be.server.product.presentation.dto.request.ProductRequest;
import kr.hhplus.be.server.product.presentation.dto.response.ProductOptionResponse;
import kr.hhplus.be.server.product.presentation.dto.response.ProductResponse;
import kr.hhplus.be.server.product.presentation.mapper.ProductOptionRequestMapper;
import kr.hhplus.be.server.product.presentation.mapper.ProductRequestMapper;
import kr.hhplus.be.server.product.presentation.mapper.ProductResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductPort productPort;

//    @GetMapping
//    public ResponseEntity<ApiResult<List<ProductResponse>>> searchProducts(
//            @RequestParam(required = false, value = "name") String name,
//            @RequestParam(required = false, value = "page", defaultValue = "0") int page,
//            @RequestParam(required = false, value = "size", defaultValue = "10") int size
//    ) {
//        return productService.getAllProducts();
//    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResult<ProductResponse>> getProduct(@PathVariable Long productId) {

        ProductResponse response = productPort.getProduct(productId);

        return ResponseEntity.ok(
                ApiResult.ok(response)
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResult<ProductResponse>> registerProduct(
            @RequestBody @Valid ProductRequest request
    ) {

        ProductResult result = productPort.registerProduct(ProductRequestMapper.toRegisterCommand(request));

        return ResponseEntity.ok(
                ApiResult.ok(ProductResponseMapper.toRegisterResponse(result))
        );
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResult<Void>> updateProduct(
            @PathVariable Long productId,
            @RequestBody @Valid ProductRequest request
    ) {

        productPort.updateProduct(productId, request.name(), request.description(), request.status());

        return ResponseEntity.ok(
                ApiResult.ok()
        );
    }

    @PostMapping("/{productId}/options")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResult<ProductResponse>> addOptions(
            @PathVariable Long productId,
            @RequestBody @Valid AddProductOptionRequest request
    ) {

        ProductResult result = productPort.addOptions(
            ProductOptionRequestMapper.toAddProductOptionCommand(productId, request));

        return ResponseEntity.ok(
                ApiResult.ok(ProductResponseMapper.toResponse(result))
        );
    }


//    @PutMapping("/{productId}/options/{optionId}")
//    public void updateOption(@PathVariable Long productId,
//                             @PathVariable Long optionId,
//                             @RequestBody UpdateProductOptionRequest request) {
//        productService.updateProductOption(productId, optionId, request);
//    }
//
//    @GetMapping("/{productId}/options/{optionId}/histories")
//    public List<ProductOptionHistoryResponse> getOptionHistories(@PathVariable Long productId,
//                                                                 @PathVariable Long optionId) {
//        return productService.getOptionHistories(productId, optionId);
//    }
}
