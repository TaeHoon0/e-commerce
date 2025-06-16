package kr.hhplus.be.server.order.infrastructure.adapter;

import java.util.List;
import kr.hhplus.be.server.order.application.dto.command.ProductCommand;
import kr.hhplus.be.server.order.application.port.out.OrderProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductAdapter implements OrderProductPort {

    @Override
    public boolean validateProduct(List<ProductCommand> commands) {

        return true;
    }
}
