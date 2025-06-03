package kr.hhplus.be.server.product.application.usecase.in;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.product.application.mapper.ProductMapper;
import kr.hhplus.be.server.product.application.port.in.ProductUseCase;
import kr.hhplus.be.server.product.domain.ProductStatus;
import kr.hhplus.be.server.product.domain.entity.Product;
import kr.hhplus.be.server.product.domain.entity.ProductOption;
import kr.hhplus.be.server.product.domain.exception.ErrorCode;
import kr.hhplus.be.server.product.domain.exception.ProductException;
import kr.hhplus.be.server.product.domain.repository.ProductRepository;
import kr.hhplus.be.server.product.presentation.dto.response.ProductOptionResponse;
import kr.hhplus.be.server.product.presentation.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse getProduct(Long productId) {

        Product product = productRepository.get(productId)
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        return ProductMapper.toDto(product);
    }

    @Override
    @Transactional
    public ProductResponse registerProduct(Product product) {

        return ProductMapper.toDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public void updateProduct(Long productId, String name, String description, ProductStatus status) {

        Product product = productRepository.get(productId)
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        product.updateIfChanged(name, description, status);
    }

    @Override
    public List<ProductOptionResponse> getOptions(Long productId) {

        Product product = productRepository.get(productId)
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        return product.getProductOptions().stream().map(ProductMapper::toDto).toList();
    }

    @Override
    @Transactional
    public ProductResponse addOptions(Long productId, List<ProductOption> options) {

    }
}
