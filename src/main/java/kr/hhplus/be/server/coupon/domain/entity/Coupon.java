package kr.hhplus.be.server.coupon.domain.entity;

import jakarta.persistence.*;
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
@Table(name = "tb_coupon")
public class Coupon extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tc_key", nullable = false)
    private Long key;

    @Column(name = "tc_name", length = 30, nullable = false)
    private String name;

    @Column(name = "tc_total_count", nullable = false)
    private Integer totalCount;

    @Column(name = "tc_remain_count", nullable = false)
    private Integer remainCount;

    @Column(name = "tc_discount_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal discountAmount;
}
