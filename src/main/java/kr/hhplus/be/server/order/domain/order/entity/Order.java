package kr.hhplus.be.server.order.domain.order.entity;

import jakarta.persistence.*;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import kr.hhplus.be.server.order.domain.BaseTimeEntity;
import kr.hhplus.be.server.order.domain.order.OrderStatus;
import kr.hhplus.be.server.order.domain.payment.entity.Payment;
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
    private Long id;

    @Column(name = "to_idempotency_key", nullable = false, unique = true)
    private String idempotencyKey;

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
    private Long userId;

    @Column(name = "to_tuc_key", nullable = false)
    private Long couponId;

    @Setter
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();

    @Setter
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Payment payment;

    @Setter
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Refund refund;


    public static Order create(long userId, List<OrderItem> items) {

        return Order.builder()
            .userId(userId)
            .status(OrderStatus.READY)
            .discountPrice(BigDecimal.ZERO)
            .totalPrice(BigDecimal.ZERO)
            .finalPrice(BigDecimal.ZERO)
            .items(items)
            .build();
    }

    public void calculateTotalPrice() {

        this.totalPrice = items.stream()
            .map(orderItem ->
                orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))
                         .setScale(2, RoundingMode.HALF_UP)
            )
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.finalPrice = totalPrice;
    }

    public void applyCoupon(long couponId, BigDecimal discountAmount) {

        this.couponId = couponId;
        this.discountPrice = this.discountPrice.add(discountAmount);
        this.finalPrice = this.finalPrice.subtract(discountAmount);
    }

    public void applyPoint(BigDecimal point) {

        this.discountPrice = this.discountPrice.add(point);
        this.finalPrice = this.finalPrice.subtract(point);
    }
}