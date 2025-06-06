package kr.hhplus.be.server.point.domain.service;

import kr.hhplus.be.server.point.domain.PointPolicy;
import kr.hhplus.be.server.point.domain.entity.Point;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointPolicy pointPolicy;

    public void charge(Point point, BigDecimal amount) {

        pointPolicy.validateMaxPoint(point.getAmount(), amount);
        point.charge(amount);
    }

    public void use(Point point, BigDecimal amount) {

        pointPolicy.validateMinPoint(point.getAmount(), amount);
        point.use(amount);
    }
}
