package kr.hhplus.be.server.product.domain.service;

import java.util.List;
import kr.hhplus.be.server.product.domain.entity.Product;
import kr.hhplus.be.server.product.domain.entity.ProductOption;
import kr.hhplus.be.server.product.domain.exception.ProductErrorCode;
import kr.hhplus.be.server.product.domain.exception.ProductException;
import org.springframework.stereotype.Component;

@Component
public class ProductPolicy {

    private static final int MAX_OPTIONS = 5;

    public void validateOptionCount(Product product, List<ProductOption> options) {

        int existing = product.getOptions().size();
        int incoming = options.size();

        if (existing + incoming > MAX_OPTIONS) {
            throw new ProductException(ProductErrorCode.EXCEED_PRODUCT_OPTION);
        }
    }

    public void validateDecrease()
}
