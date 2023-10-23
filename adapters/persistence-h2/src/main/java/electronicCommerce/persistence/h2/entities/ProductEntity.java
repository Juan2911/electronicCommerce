package electronicCommerce.persistence.h2.entities;

import electronicCommerce.domain.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = ProductEntity.PRODUCT)
public class ProductEntity {
    static final String PRODUCT = "product";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private BrandEntity brandEntity;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "price_list")
    private int priceList;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "priority")
    private int priority;
    @Column(precision = 8, scale = 2)
    private BigDecimal price;
    @Column(name = "currency")
    private String currency;

    public static Product toProduct(ProductEntity productEntity) {
        return Product.builder()
                .brandId(productEntity.getBrandEntity().getId())
                .startDate(productEntity.getStartDate())
                .endDate(productEntity.getEndDate())
                .priceListId(productEntity.getPriceList())
                .productId(productEntity.getProductId())
                .priority(productEntity.getPriority())
                .price(productEntity.getPrice())
                .currency(productEntity.getCurrency())
                .build();
    }
}
