package kr.hhplus.be.server.coupon.application.port.in;

import kr.hhplus.be.server.coupon.application.dto.command.CreateTemplateCommand;
import kr.hhplus.be.server.coupon.application.dto.result.TemplateResult;

public interface TemplatePort {

    TemplateResult createTemplate(CreateTemplateCommand command);
}
