package kr.hhplus.be.server.coupon.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
        @AttributeOverride(name = "createdDate", column = @Column(name = "tc_reg_date")),
        @AttributeOverride(name = "modifiedDate", column = @Column(name = "tc_mod_date"))
})
@Table(name = "tb_coupon_template")
public class CouponTemplate extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tcp_key", nullable = false)
    private Long id;

    @Column(name = "tcp_name", length = 30, nullable = false)
    private String name;

    @Column(name = "tcp_total_count", nullable = false)
    private Integer totalCount;

    @Column(name = "tcp_remain_count", nullable = false)
    private Integer remainCount;

    @Column(name = "tcp_discount_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal discountAmount;

    @Column(name = "tcp_minimum_price", precision = 15, scale = 2, nullable = true)
    private BigDecimal minimumPrice;

    @Column(name = "tcp_expire_date", nullable = true)
    private LocalDateTime expireDate;

    public static CouponTemplate create(
        String name, Integer totalCount, BigDecimal discountAmount, BigDecimal minimumPrice, LocalDateTime expireDate
    ) {

        return CouponTemplate.builder()
            .name(name)
            .totalCount(totalCount)
            .remainCount(totalCount)
            .discountAmount(discountAmount)
            .minimumPrice(minimumPrice)
            .expireDate(expireDate)
            .build();
    }
}
