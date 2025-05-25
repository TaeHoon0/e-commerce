package kr.hhplus.be.server.point.domain.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.point.domain.PointChangedType;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
        @AttributeOverride(name = "createdDate", column = @Column(name = "tph_reg_date")),
        @AttributeOverride(name = "modifiedDate", column = @Column(name = "tph_mod_date"))
})
@Table(name = "tb_point_history")
public class PointHistory extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tph_key", nullable = false)
    private Long id;

    @Column(name = "tph_changed_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal changedAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "tph_changed_type", nullable = false)
    private PointChangedType changedType;

    @ManyToOne
    @JoinColumn(name = "tph_tp_key", nullable = false)
    private Point point;

    public static PointHistory from(Point point, BigDecimal changedAmount,  PointChangedType changedType) {

        return PointHistory.builder()
                .point(point)
                .changedAmount(changedAmount)
                .changedType(changedType)
                .build();
    }
}
