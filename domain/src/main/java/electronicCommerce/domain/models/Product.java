package electronicCommerce.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @JsonProperty("BRAND_ID")
    private int brandId;
    @JsonProperty("START_DATE")
    private LocalDateTime startDate;
    @JsonProperty("END_DATE")
    private LocalDateTime endDate;
    @JsonProperty("PRICE_LIST")
    private int priceListId;
    @JsonProperty("PRODUCT_ID")
    private int productId;
    @JsonProperty("PRIORITY")
    private int priority;
    @JsonProperty("PRICE")
    private BigDecimal price;
    @JsonProperty("CURRENCY")
    private String currency;
}
