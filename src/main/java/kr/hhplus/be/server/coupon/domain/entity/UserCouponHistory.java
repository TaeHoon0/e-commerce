package kr.hhplus.be.server.coupon.domain.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.hhplus.be.server.coupon.domain.CouponChangeType;
import kr.hhplus.be.server.coupon.domain.CouponStatus;
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
    @AttributeOverride(name = "createdDate", column = @Column(name = "tuch_reg_date")),
    @AttributeOverride(name = "modifiedDate", column = @Column(name = "tuch_mod_date"))
})
@Table(name = "tb_user_coupon_history")
public class UserCouponHistory extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tuch_key")
    private Long id;

    @Column(name = "tuch_user_key")
    private Long userId;

    @Column(name = "tuch_order_key")
    private Long orderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "tuch_change_type")
    private CouponChangeType changeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "tuch_before_status", nullable = true)
    private CouponStatus beforeStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "tuch_after_status")
    private CouponStatus afterStatus;

    @ManyToOne
    @JoinColumn(name = "tuch_tuc_key")
    private UserCoupon userCoupon;

    public static UserCouponHistory createIssueHistory(long userId, UserCoupon userCoupon) {

        return UserCouponHistory.builder()
            .userId(userId)
            .changeType(CouponChangeType.ISSUE)
            .beforeStatus(CouponStatus.UNASSIGNED)
            .afterStatus(CouponStatus.AVAILABLE)
            .userCoupon(userCoupon)
            .build();
    }

    public static UserCouponHistory createUseHistory(long userId, long orderId, UserCoupon userCoupon) {

        return UserCouponHistory.builder()
                .userId(userId)
                .orderId(orderId)
                .changeType(CouponChangeType.USE)
                .beforeStatus(CouponStatus.AVAILABLE)
                .afterStatus(CouponStatus.USED)
                .userCoupon(userCoupon)
                .build();
    }
}
