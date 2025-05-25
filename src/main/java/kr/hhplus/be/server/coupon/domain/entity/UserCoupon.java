package kr.hhplus.be.server.coupon.domain.entity;

import jakarta.persistence.*;
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
    private Long key;

    @Enumerated(EnumType.STRING)
    @Column(name = "tuc_status", length = 10, nullable = false)
    private CouponStatus status;

    @Column(name = "tuc_to_key", nullable = true)
    private Long orderKey;

    @Column(name = "tuc_tu_key", nullable = false)
    private Long userKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tuc_tc_key", nullable = false)
    private Coupon coupon;
}
