package kr.hhplus.be.server.product.presentation.dto.request;

import kr.hhplus.be.server.product.domain.ProductStatus;
import org.hibernate.validator.constraints.Length;

public record ProductRequest(
        @Length(max = 50) String name,
        @Length(max = 255) String description,
        ProductStatus status
) {
}
