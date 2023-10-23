package electronicCommerce.domain.utils;

import electronicCommerce.domain.models.Product;
import electronicCommerce.domain.models.RequestDomain;
import electronicCommerce.domain.models.impl.GetProductRequest;

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

    public static RequestDomain buildGetProductRequest() {
        return GetProductRequest.builder()
                .date(DateMapper.fromStringToLocalDateTime("2020-06-14-17.00.00"))
                .brandId(1)
                .productId(1)
                .build();
    }
}
