package kr.hhplus.be.server.order.domain.order.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.order.domain.BaseTimeEntity;
import kr.hhplus.be.server.order.domain.order.OrderStatus;
import kr.hhplus.be.server.order.domain.order.event.OrderCreatedEvent;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
        @AttributeOverride(name = "createdDate", column = @Column(name = "tos_reg_date")),
        @AttributeOverride(name = "modifiedDate", column = @Column(name = "tos_mod_date"))
})
@Table(name = "tb_order_snapshot")
public class OrderSnapShot extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tos_key", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tos_befor_status")
    private OrderStatus beforeStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "tos_after_status")
    private OrderStatus afterStatus;

    @Column(name = "tos_total_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "tos_discount_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal discountPrice;

    @Column(name = "tos_final_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal finalPrice;

    @Column(name = "tos_snapshot_json", columnDefinition = "json", nullable = false)
    private String snapshotJson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tos_to_key", nullable = false)
    private Order order;


    public static OrderSnapShot ofCreated(OrderCreatedEvent event, String snapshotJson) {

        return OrderSnapShot.builder()
            .beforeStatus(null)
            .afterStatus(event.getOrder().getStatus())
            .totalPrice(event.getOrder().getTotalPrice())
            .discountPrice(event.getOrder().getDiscountPrice())
            .finalPrice(event.getOrder().getFinalPrice())
            .snapshotJson(snapshotJson)
            .order(event.getOrder())
            .build();
    }
}
