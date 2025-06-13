package kr.hhplus.be.server.product.domain.service;

import kr.hhplus.be.server.product.domain.entity.ProductOption;
import kr.hhplus.be.server.product.domain.repository.ProductOptionQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductOptionQueryRepository productOptionQueryRepository;

    public List<ProductOption> decreaseProductOption(List<Long> optionIds) {

        List<ProductOption> options = productOptionQueryRepository.findByOptionIdsWithLock(optionIds);

    }
}
