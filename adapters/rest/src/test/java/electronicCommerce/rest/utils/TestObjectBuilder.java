package electronicCommerce.rest.utils;

import electronicCommerce.domain.models.Product;
import electronicCommerce.domain.utils.DateMapper;

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
}
