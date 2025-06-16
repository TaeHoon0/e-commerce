package kr.hhplus.be.server.coupon.presentation.mapper;

import kr.hhplus.be.server.coupon.application.dto.command.CreateTemplateCommand;
import kr.hhplus.be.server.coupon.presentation.dto.request.TemplateRequest;

public class TemplateRequestMapper {

    public static CreateTemplateCommand toCreateCommand(TemplateRequest request) {

        return new CreateTemplateCommand(
            request.templateName(),
            request.totalCount(),
            request.discountAmount(),
            request.minimumPrice(),
            request.expireDate(),
            request.type()
        );
    }
}
