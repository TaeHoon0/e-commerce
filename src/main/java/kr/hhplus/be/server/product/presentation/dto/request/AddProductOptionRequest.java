package kr.hhplus.be.server.product.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record AddProductOptionRequest(

    @NotEmpty
    List<ProductOptionRequest> productOptionRequests

) {
}
