package kr.hhplus.be.server.coupon.presentation.mapper;

import kr.hhplus.be.server.coupon.application.dto.response.TemplateResult;
import kr.hhplus.be.server.coupon.presentation.dto.reseponse.CreateTemplateResponse;

public class TemplateResponseMapper {

    public static CreateTemplateResponse toResponse(TemplateResult result) {

        return new CreateTemplateResponse(
            result.templateId(),
            result.totalCount()
        );
    }
}
