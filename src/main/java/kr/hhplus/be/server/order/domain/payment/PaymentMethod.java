package kr.hhplus.be.server.order.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PaymentMethod {

    CARD            ("신용카드"),
    DirectBank      ("계좌이체"),
    HPP             ("핸드폰"),
    POINT           ("포인트")
    ;

    String desc;
}
