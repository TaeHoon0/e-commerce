package kr.hhplus.be.server.product.application.usecase.in;

import jakarta.transaction.Transactional;
import java.util.List;
import kr.hhplus.be.server.product.application.dto.command.AddProductOptionCommand;
import kr.hhplus.be.server.product.application.dto.command.DecreaseStockCommand;
import kr.hhplus.be.server.product.application.dto.ProductOptionDto;
import kr.hhplus.be.server.product.application.dto.command.RegisterProductCommand;
import kr.hhplus.be.server.product.application.dto.command.ValidateStockQuery;
import kr.hhplus.be.server.product.application.dto.result.ProductResult;
import kr.hhplus.be.server.product.application.mapper.ProductMapper;
import kr.hhplus.be.server.product.application.mapper.ProductResultMapper;
import kr.hhplus.be.server.product.application.port.in.ProductPort;
import kr.hhplus.be.server.product.domain.ProductStatus;
import kr.hhplus.be.server.product.domain.entity.Product;
import kr.hhplus.be.server.product.domain.entity.ProductOption;
import kr.hhplus.be.server.product.domain.exception.ProductErrorCode;
import kr.hhplus.be.server.product.domain.exception.ProductException;
import kr.hhplus.be.server.product.domain.repository.ProductQueryRepository;
import kr.hhplus.be.server.product.domain.service.ProductOptionService;
import kr.hhplus.be.server.product.domain.service.ProductService;
import kr.hhplus.be.server.product.presentation.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductUseCase implements ProductPort {

    private final ProductService productService;
    private final ProductQueryRepository productQueryRepository;
    private final ProductOptionService productOptionService;

    @Override
    public ProductResponse getProduct(Long productId) {

        Product product = productQueryRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));

        return ProductMapper.toDto(product);
    }

    @Override
    @Transactional
    public ProductResult registerProduct(RegisterProductCommand command) {

        Product product = productService.register(command.name(), command.description());

        return ProductResultMapper.toResult(product);
    }

    @Override
    @Transactional
    public void updateProduct(Long productId, String name, String description, ProductStatus status) {

        Product product = productQueryRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));

        product.updateIfChanged(name, description, status);
    }

    @Override
    @Transactional
    public ProductResult addOptions(AddProductOptionCommand command) {

        List<ProductOption> options = command.optionCommands().stream()
            .map(option ->
                ProductOption.create(option.optionName(), option.quantity(), option.price())
            ).toList();

        Product product = productService.addOptions(command.productId(), options);


        return ProductResultMapper.toResult(product);
    }

    @Override
    @Transactional
    public void decreaseStock(DecreaseStockCommand command) {

        // TODO 재고 감소, 이력 저장
        productOptionService.decreaseProductOption(
                command.items().stream().map(ProductOptionDto::optionId).toList());
    }

    @Override
    public boolean validateStock(ValidateStockQuery query) {
        return true;
    }
}
