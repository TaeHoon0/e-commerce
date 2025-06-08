package kr.hhplus.be.server.product.domain.service;

import java.util.List;
import kr.hhplus.be.server.product.domain.entity.Product;
import kr.hhplus.be.server.product.domain.entity.ProductOption;
import kr.hhplus.be.server.product.domain.exception.ProductErrorCode;
import kr.hhplus.be.server.product.domain.exception.ProductException;
import kr.hhplus.be.server.product.domain.repository.ProductCommandRepository;
import kr.hhplus.be.server.product.domain.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductPolicy productPolicy;
    private final ProductQueryRepository productQueryRepository;
    private final ProductCommandRepository productCommandRepository;

    public Product register(String name, String description) {

        if (productQueryRepository.findByProductName(name).isPresent())
            throw new ProductException(ProductErrorCode.DUPLICATE_PRODUCT_NAME);

        return productCommandRepository.save(Product.create(name, description));
    }

    public Product addOptions(long productId, List<ProductOption> options) {

        Product product = productQueryRepository.findByProductId(productId)
            .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));

        productPolicy.validateOptionCount(product, options);

        product.addOptions(options);

        return product;
    }
}
