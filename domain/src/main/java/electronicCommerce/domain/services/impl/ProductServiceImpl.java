package electronicCommerce.domain.services.impl;

import electronicCommerce.domain.models.Product;
import electronicCommerce.domain.models.RequestDomain;
import electronicCommerce.domain.models.impl.GetProductRequest;
import electronicCommerce.domain.ports.ProductPersistence;
import electronicCommerce.domain.services.ProductService;
import electronicCommerce.domain.utils.DateMapper;
import electronicCommerce.domain.validations.ValidationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ValidationService productServiceValidationServiceImpl;
    private final ProductPersistence productPersistenceH2Adapter;

    @Override
    public Product readProductBy(String date, int productId, int brandId) {
        LocalDateTime localDateTime = DateMapper.fromStringToLocalDateTime(date);

        RequestDomain getProductRequest = GetProductRequest.builder()
                .date(localDateTime)
                .productId(productId)
                .brandId(brandId)
                .build();

        productServiceValidationServiceImpl.validate(getProductRequest);

        log.info("Validation Successfully");

        return productPersistenceH2Adapter.findByDateAndProductIdAndBrandId(getProductRequest);
    }
}
