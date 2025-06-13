package kr.hhplus.be.server.order.domain.order.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.order.domain.BaseTimeEntity;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
        @AttributeOverride(name = "createdDate", column = @Column(name = "toi_reg_date")),
        @AttributeOverride(name = "modifiedDate", column = @Column(name = "toi_mod_date"))
})
@Table(name = "tb_order_item")
public class OrderItem extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toi_key", nullable = false)
    private Long id;

    @Column(name = "toi_product_name", length = 50, nullable = false)
    private String productName;

    @Column(name = "toi_product_option_name", length = 50, nullable = false)
    private String productOptionName;

    @Column(name = "toi_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "toi_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toi_to_key", nullable = false)
    private Order order;


    public static OrderItem create(
        String productName, String productOptionName, Integer quantity, BigDecimal price
    ) {

        return OrderItem.builder()
            .productName(productName)
            .productOptionName(productOptionName)
            .quantity(quantity)
            .price(price)
            .build();
    }
}
