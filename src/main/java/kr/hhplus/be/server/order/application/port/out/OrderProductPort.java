package kr.hhplus.be.server.order.application.port.out;

import java.util.List;
import kr.hhplus.be.server.order.application.dto.command.ProductCommand;

public interface OrderProductPort {

    boolean validateProduct(List<ProductCommand> commands);
}
