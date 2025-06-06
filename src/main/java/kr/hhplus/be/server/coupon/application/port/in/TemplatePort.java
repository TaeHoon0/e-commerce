package kr.hhplus.be.server.coupon.application.port.in;

import kr.hhplus.be.server.coupon.application.dto.request.CreateTemplateCommand;
import kr.hhplus.be.server.coupon.application.dto.response.TemplateResult;

public interface TemplatePort {

    TemplateResult createTemplate(CreateTemplateCommand command);
}
