package kr.hhplus.be.server.product.domain.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.product.domain.ProductChangedType;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
        @AttributeOverride(name = "createdDate", column = @Column(name = "tpoh_reg_date")),
        @AttributeOverride(name = "modifiedDate", column = @Column(name = "tpoh_mod_date"))
})
@Table(name = "tb_product_option_history")
public class ProductOptionHistory extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tpoh_key", nullable = false)
    private Long key;

    @Enumerated(EnumType.STRING)
    @Column(name = "tpoh_changed_type", length = 10, nullable = false)
    private ProductChangedType changedType;

    @Column(name = "tpoh_changed_quantity", nullable = false)
    private Integer changedQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tpoh_tpo_key", nullable = false)
    private ProductOption productOption;
}
