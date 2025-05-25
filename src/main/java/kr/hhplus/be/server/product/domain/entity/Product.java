package kr.hhplus.be.server.product.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
        @AttributeOverride(name = "createdDate", column = @Column(name = "tp_reg_date")),
        @AttributeOverride(name = "modifiedDate", column = @Column(name = "tp_mod_date"))
})
@Table(name = "tb_product")
public class Product extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tp_key", nullable = false)
    private Long key;

    @Column(name = "tp_name", length = 50, nullable = false)
    private String name;

    @Column(name = "tp_description", nullable = false)
    private String description;
}
