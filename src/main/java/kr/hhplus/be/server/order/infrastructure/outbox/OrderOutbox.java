package kr.hhplus.be.server.order.infrastructure.outbox;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_order_outbox")
public class OrderOutbox {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "too_key")
    private Long id;

    @Column(name = "too_tp_id", length = 50, nullable = false)
    private Long orderId;

    @Column(name = "too_order_status", length = 100, nullable = false)
    private String orderStatus;

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
}
