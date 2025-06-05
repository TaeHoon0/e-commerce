package kr.hhplus.be.server.coupon.application.port.in;

import kr.hhplus.be.server.coupon.application.dto.request.CreateTemplateCommand;

public interface TemplatePort {

    void createTemplate(CreateTemplateCommand command);
}
