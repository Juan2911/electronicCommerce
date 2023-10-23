package electronicCommerce.domain.models.impl;

import electronicCommerce.domain.models.RequestDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductRequest implements RequestDomain {
    private LocalDateTime date;
    private int productId;
    private int brandId;
}
