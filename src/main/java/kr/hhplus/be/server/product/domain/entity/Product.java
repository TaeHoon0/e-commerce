package kr.hhplus.be.server.product.domain.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.product.domain.ProductStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private Long id;

    @Column(name = "tp_name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "tp_description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "tp_status", length = 10, nullable = false)
    private ProductStatus status;

    @Builder.Default
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductOption> options = new ArrayList<>();

    public static Product create(String name, String description) {

        return Product.builder()
                .name(name)
                .description(description)
                .status(ProductStatus.INACTIVE)
                .build();
    }

    public void addOption(ProductOption option) {
        option.setProduct(this);
        this.options.add(option);
    }

    public void addOptions(List<ProductOption> options) {
        options.forEach(this::addOption);
    }

    public void changeStatus(ProductStatus status) {
        this.status = status;
    }

    public void updateIfChanged(String name, String description, ProductStatus status) {
        if (!Objects.equals(this.name, name))
            this.name = name;

        if (!Objects.equals(this.description, description))
            this.description = description;

        if (status != null && this.status != status)
            this.changeStatus(status);
    }
}
