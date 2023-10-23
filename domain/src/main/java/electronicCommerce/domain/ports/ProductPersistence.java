package electronicCommerce.domain.ports;

import electronicCommerce.domain.exceptions.NotFoundException;
import electronicCommerce.domain.models.Product;
import electronicCommerce.domain.models.RequestDomain;

public interface ProductPersistence {
    Product findByDateAndProductIdAndBrandId(final RequestDomain requestDomain) throws NotFoundException;
}
