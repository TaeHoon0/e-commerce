package kr.hhplus.be.server.product.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
        @AttributeOverride(name = "createdDate", column = @Column(name = "tpo_reg_date")),
        @AttributeOverride(name = "modifiedDate", column = @Column(name = "tpo_mod_date"))
})
@Table(name = "tb_product_option")
public class ProductOption extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tpo_key", nullable = false)
    private Long key;

    @Column(name = "tpo_option_name", length = 50, nullable = false)
    private String optionName;

    @Column(name = "tpo_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "tpo_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tpo_tp_key", nullable = false)
    private Product product;

    public static ProductOption createNew(String optionName, Integer quantity, BigDecimal price) {

        return ProductOption.builder()
                .optionName(optionName)
                .quantity(quantity)
                .price(price)
                .build();
    }
}
