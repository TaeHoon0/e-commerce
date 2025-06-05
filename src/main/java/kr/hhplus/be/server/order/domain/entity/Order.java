package kr.hhplus.be.server.order.domain.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.order.domain.OrderStatus;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
        @AttributeOverride(name = "createdDate", column = @Column(name = "to_reg_date")),
        @AttributeOverride(name = "modifiedDate", column = @Column(name = "to_mod_date"))
})
@Table(name = "tb_order")
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "to_key", nullable = false)
    private Long key;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_status", length = 10, nullable = false)
    private OrderStatus status;

    @Column(name = "to_total_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "to_discount_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal discountPrice;

    @Column(name = "to_final_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal finalPrice;

    @Column(name = "to_tu_key", nullable = false)
    private Long userKey;

    @Column(name = "to_tuc_key", nullable = false)
    private Long userCouponKey;
}
