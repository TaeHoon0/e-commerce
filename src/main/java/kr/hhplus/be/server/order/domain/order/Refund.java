package kr.hhplus.be.server.order.domain.order;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import kr.hhplus.be.server.order.domain.BaseTimeEntity;
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
    @AttributeOverride(name = "createdDate", column = @Column(name = "tr_reg_date")),
    @AttributeOverride(name = "modifiedDate", column = @Column(name = "tr_mod_date"))
})
@Table(name = "tb_order_refund")
public class Refund extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tr_key")
    private Long id;

    @Column(name = "tr_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "tr_reason")
    private String reason;

    @Column(name = "tr_point")
    private Integer point;

    @Column(name = "tr_refund_date", nullable = false)
    private LocalDateTime refundDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tr_to_key")
    private Order order;
}
