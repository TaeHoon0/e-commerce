package kr.hhplus.be.server.point.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
        @AttributeOverride(name = "createdDate", column = @Column(name = "tp_reg_date")),
        @AttributeOverride(name = "modifiedDate", column = @Column(name = "tp_mod_date"))
})
@Table(name = "tb_point")
public class Point extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tp_key", nullable = false)
    private Long id;

    @Column(name = "tp_amount", precision = 15, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "tp_tu_key", nullable = false, unique = true)
    private Long userId;

    @OneToMany(mappedBy = "point", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PointHistory> histories = new ArrayList<>();


    public void charge(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    public void use(BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }

    public static Point create(Long userId) {

        return Point.builder()
                .userId(userId)
                .amount(BigDecimal.ZERO)
                .build();
    }
}
