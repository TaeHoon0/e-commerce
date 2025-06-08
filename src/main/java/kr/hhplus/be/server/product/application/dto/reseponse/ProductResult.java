package kr.hhplus.be.server.product.application.dto.reseponse;

import java.util.List;
import kr.hhplus.be.server.product.domain.ProductStatus;

public record ProductResult (

    long id,
    String name,
    String description,
    ProductStatus status,
    List<ProductOptionResult> options
) {

}
