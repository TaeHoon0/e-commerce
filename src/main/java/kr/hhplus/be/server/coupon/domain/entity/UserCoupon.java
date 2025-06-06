package kr.hhplus.be.server.coupon.domain.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import kr.hhplus.be.server.coupon.domain.CouponStatus;

import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
        @AttributeOverride(name = "createdDate", column = @Column(name = "tuc_reg_date")),
        @AttributeOverride(name = "modifiedDate", column = @Column(name = "tuc_mod_date"))
})
@Table(name = "tb_user_coupon")
public class UserCoupon extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tuc_key", nullable = false)
    private Long id;

    @Column(name = "tuc_name", length = 30, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "tuc_status", length = 10, nullable = false)
    private CouponStatus status;

    @Column(name = "tuc_discount_amount", nullable = false)
    private BigDecimal discountAmount;

    @Column(name = "tuc_minimum_price")
    private BigDecimal minimumPrice;

    @Column(name = "tuc_expire_date")
    private LocalDateTime expireDate;

    @Column(name = "tuc_to_key", nullable = true)
    private Long orderId;

    @Column(name = "tuc_tu_key", nullable = true)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tuc_tcp_key", nullable = false)
    private CouponTemplate couponTemplate;

    public static UserCoupon create(
        String name, BigDecimal discountAmount, BigDecimal minimumPrice, LocalDateTime expireDate, CouponTemplate couponTemplate
    ) {

        return UserCoupon.builder()
            .name(name)
            .status(CouponStatus.AVAILABLE)
            .discountAmount(discountAmount)
            .minimumPrice(minimumPrice)
            .expireDate(expireDate)
            .couponTemplate(couponTemplate)
            .build();
    }
}
