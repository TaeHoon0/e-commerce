package kr.hhplus.be.server.order.infrastructure.adapter;

import java.math.BigDecimal;
import kr.hhplus.be.server.order.application.port.out.OrderPointPort;
import kr.hhplus.be.server.point.application.port.in.PointPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PointAdapter implements OrderPointPort {

    private final PointPort pointPort;

    @Override
    public boolean validatePoint(long userId, BigDecimal pointAmount) {

        return pointPort.validatePoint(userId, pointAmount);
    }

}
