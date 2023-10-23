package electronicCommerce.persistence.h2.adapters;

import electronicCommerce.domain.exceptions.NotFoundException;
import electronicCommerce.domain.models.Product;
import electronicCommerce.domain.models.RequestDomain;
import electronicCommerce.domain.models.impl.GetProductRequest;
import electronicCommerce.domain.ports.ProductPersistence;
import electronicCommerce.persistence.h2.entities.ProductEntity;
import electronicCommerce.persistence.h2.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductPersistenceH2Adapter implements ProductPersistence {

    private final ProductRepository productRepository;

    @Override
    public Product findByDateAndProductIdAndBrandId(RequestDomain requestDomain) throws NotFoundException {
        GetProductRequest getProductRequest = (GetProductRequest) requestDomain;

        log.info("Getting Product from Database");
        List<ProductEntity> productEntities = productRepository
                .findByDateBetweenAndProductIdAndBrandId(getProductRequest.getDate(), getProductRequest.getProductId(), getProductRequest.getBrandId());

        if (productEntities.isEmpty()) {
            log.warn("Product Not Found");
            throw new NotFoundException("Product Not Found");
        }

        ProductEntity productEntity = productEntities.stream()
                .max(Comparator.comparingInt(ProductEntity::getPriority)).get();

        return ProductEntity.toProduct(productEntity);
    }
}
