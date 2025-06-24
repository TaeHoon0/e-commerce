package kr.hhplus.be.server.order.infrastructure.outbox;

import jakarta.persistence.*;
import kr.hhplus.be.server.order.domain.order.OrderStatus;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.order.event.OrderCreatedEvent;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_order_outbox")
public class OrderOutbox {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "too_key")
    private Long id;

    @Column(name = "too_to_key", length = 50, nullable = false)
    private Long orderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "too_order_status", length = 100, nullable = false)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "too_status")
    private OutboxStatus outboxStatus;

    @Column(name = "too_payload", columnDefinition = "json", nullable = false)
    private String payload;

    @CreatedDate
    @Column(name = "too_reg_date", updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "too_mod_date")
    private LocalDateTime modifiedDate;


    public static OrderOutbox ofCreated(OrderCreatedEvent event, String payload) {

        return OrderOutbox.builder()
            .orderId(event.getOrder().getId())
            .orderStatus(event.getOrder().getStatus())
            .outboxStatus(OutboxStatus.PENDING)
            .payload(payload)
            .build();
    }

    public void sent() {

        this.outboxStatus = OutboxStatus.SENT;
    }

    public void fail() {

        this.outboxStatus = OutboxStatus.FAILED;
    }
}
