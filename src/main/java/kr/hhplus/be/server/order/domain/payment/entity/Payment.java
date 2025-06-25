package kr.hhplus.be.server.order.domain.payment.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

import kr.hhplus.be.server.order.domain.BaseTimeEntity;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.payment.PG;
import kr.hhplus.be.server.order.domain.payment.PaymentMethod;
import kr.hhplus.be.server.order.domain.payment.PaymentStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
    @AttributeOverride(name = "createdDate", column = @Column(name = "tp_reg_date")),
    @AttributeOverride(name = "modifiedDate", column = @Column(name = "tp_mod_date"))
})
@Table(name = "tb_order_payment")
public class Payment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tp_key")
    private Long id;

    @Column(name = "tp_tu_key", nullable = false)
    private Long userId;

    @Column(name = "tp_tid")
    private String tid;

    @Enumerated(EnumType.STRING)
    @Column(name = "tp_status", nullable = false)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "tp_pg", nullable = false)
    private PG pg;

    @Enumerated(EnumType.STRING)
    @Column(name = "tp_method", nullable = false)
    private PaymentMethod method;

    @Column(name = "tp_total_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "tp_final_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal finalPrice;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tp_to_key")
    private Order order;


    public static Payment create(
        long userId, PG pg, PaymentMethod method, Order order
    ) {

        return Payment.builder()
            .userId(userId)
            .pg(pg)
            .method(method)
            .status(PaymentStatus.READY)
            .totalPrice(order.getTotalPrice())
            .finalPrice(order.getFinalPrice())
            .order(order)
            .build();
    }
}
