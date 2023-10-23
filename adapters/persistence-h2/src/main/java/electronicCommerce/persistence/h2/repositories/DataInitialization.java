package electronicCommerce.persistence.h2.repositories;

import electronicCommerce.domain.utils.DateMapper;
import electronicCommerce.persistence.h2.entities.BrandEntity;
import electronicCommerce.persistence.h2.entities.ProductEntity;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class DataInitialization implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    @Override
    public void run(String... args) throws Exception {
        BrandEntity brandEntity = BrandEntity.builder()
                .name("ZARA")
                .build();

        BrandEntity brandEntitySaved = brandRepository.save(brandEntity);

        List<ProductEntity> productEntities = new ArrayList<>();
        ProductEntity productEntity1 = ProductEntity.builder()
                .brandEntity(brandEntitySaved)
                .startDate(DateMapper.fromStringToLocalDateTime("2020-06-14-00.00.00"))
                .endDate(DateMapper.fromStringToLocalDateTime("2020-12-31-23.59.59"))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(new BigDecimal(35.50))
                .currency("EUR")
                .build();

        ProductEntity productEntity2 = ProductEntity.builder()
                .brandEntity(brandEntitySaved)
                .startDate(DateMapper.fromStringToLocalDateTime("2020-06-14-15.00.00"))
                .endDate(DateMapper.fromStringToLocalDateTime("2020-06-14-18.30.00"))
                .priceList(2)
                .productId(35455)
                .priority(1)
                .price(new BigDecimal(25.45))
                .currency("EUR")
                .build();

        ProductEntity productEntity3 = ProductEntity.builder()
                .brandEntity(brandEntitySaved)
                .startDate(DateMapper.fromStringToLocalDateTime("2020-06-15-00.00.00"))
                .endDate(DateMapper.fromStringToLocalDateTime("2020-06-15-11.00.00"))
                .priceList(3)
                .productId(35455)
                .priority(1)
                .price(new BigDecimal(30.50))
                .currency("EUR")
                .build();

        ProductEntity productEntity4 = ProductEntity.builder()
                .brandEntity(brandEntitySaved)
                .startDate(DateMapper.fromStringToLocalDateTime("2020-06-15-16.00.00"))
                .endDate(DateMapper.fromStringToLocalDateTime("2020-12-31-23.59.59"))
                .priceList(4)
                .productId(35455)
                .priority(1)
                .price(new BigDecimal(38.95))
                .currency("EUR")
                .build();

        productEntities.add(productEntity1);
        productEntities.add(productEntity2);
        productEntities.add(productEntity3);
        productEntities.add(productEntity4);
            productRepository.saveAll(productEntities);
    }
}
