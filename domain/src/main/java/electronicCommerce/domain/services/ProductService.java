package electronicCommerce.domain.services;

import electronicCommerce.domain.models.Product;

public interface ProductService {
    Product readProductBy(final String date, final int productId, final int brandId);
}