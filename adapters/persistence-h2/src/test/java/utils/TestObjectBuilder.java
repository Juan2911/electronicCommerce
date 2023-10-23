package utils;

import electronicCommerce.domain.models.Product;
import electronicCommerce.domain.models.RequestDomain;
import electronicCommerce.domain.models.impl.GetProductRequest;
import electronicCommerce.domain.utils.DateMapper;
import electronicCommerce.persistence.h2.entities.BrandEntity;
import electronicCommerce.persistence.h2.entities.ProductEntity;

import java.math.BigDecimal;

public class TestObjectBuilder {

    public static Product buildAProductWithPriority() {
        return Product.builder()
                .brandId(1)
                .startDate(DateMapper.fromStringToLocalDateTime("2020-06-14-15.00.00"))
                .endDate(DateMapper.fromStringToLocalDateTime("2020-06-14-18.30.00"))
                .priceListId(2)
                .productId(35455)
                .priority(1)
                .price(new BigDecimal(25.45))
                .currency("EUR")
                .build();
    }

    public static Product buildAProductWithoutPriority() {
        return Product.builder()
                .brandId(1)
                .startDate(DateMapper.fromStringToLocalDateTime("2020-06-14-00.00.00"))
                .endDate(DateMapper.fromStringToLocalDateTime("2020-12-31-23.59.59"))
                .priceListId(1)
                .productId(35455)
                .priority(0)
                .price(new BigDecimal(35.50))
                .currency("EUR")
                .build();
    }

    public static ProductEntity buildAProductEntityWithoutPriority() {
        return ProductEntity.builder()
                .brandEntity(buildEntityBrand())
                .startDate(DateMapper.fromStringToLocalDateTime("2020-06-14-00.00.00"))
                .endDate(DateMapper.fromStringToLocalDateTime("2020-12-31-23.59.59"))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(new BigDecimal(35.50))
                .currency("EUR")
                .build();
    }

    public static ProductEntity buildAProductEntityWithPriority() {
        return ProductEntity.builder()
                .brandEntity(buildEntityBrand())
                .startDate(DateMapper.fromStringToLocalDateTime("2020-06-14-15.00.00"))
                .endDate(DateMapper.fromStringToLocalDateTime("2020-06-14-18.30.00"))
                .priceList(2)
                .productId(35455)
                .priority(1)
                .price(new BigDecimal(25.45))
                .currency("EUR")
                .build();
    }

    public static BrandEntity buildEntityBrand() {
        return BrandEntity.builder()
                .name("ZARA")
                .id(1)
                .build();
    }

    public static RequestDomain buildGetProductRequest() {
        return GetProductRequest.builder()
                .date(DateMapper.fromStringToLocalDateTime("2020-06-14-17.00.00"))
                .brandId(1)
                .productId(1)
                .build();
    }
}
