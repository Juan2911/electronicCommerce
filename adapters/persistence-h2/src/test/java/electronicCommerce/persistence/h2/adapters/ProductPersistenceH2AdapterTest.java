package electronicCommerce.persistence.h2.adapters;

import electronicCommerce.domain.exceptions.NotFoundException;
import electronicCommerce.domain.models.Product;
import electronicCommerce.domain.models.impl.GetProductRequest;
import electronicCommerce.persistence.h2.entities.ProductEntity;
import electronicCommerce.persistence.h2.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import utils.TestObjectBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class ProductPersistenceH2AdapterTest {

    private ProductPersistenceH2Adapter productPersistenceH2Adapter;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        productPersistenceH2Adapter = new ProductPersistenceH2Adapter(productRepository);
    }

    @Test
    public void shouldReturnJustAProduct() {
        GetProductRequest getProductRequest = (GetProductRequest) TestObjectBuilder.buildGetProductRequest();
        ProductEntity productEntity = TestObjectBuilder.buildAProductEntityWithoutPriority();
        List<ProductEntity> productEntities = new ArrayList<>();
        Product product = TestObjectBuilder.buildAProductWithoutPriority();
        productEntities.add(productEntity);

        when(productRepository.findByDateBetweenAndProductIdAndBrandId(getProductRequest.getDate(),
                getProductRequest.getProductId(),
                getProductRequest.getBrandId())).thenReturn(productEntities);

        Product productResponse = productPersistenceH2Adapter.findByDateAndProductIdAndBrandId(getProductRequest);

        assertEquals(product, productResponse);
    }

    @Test
    public void shouldReturnMostPriorityProduct() {
        GetProductRequest getProductRequest = (GetProductRequest) TestObjectBuilder.buildGetProductRequest();
        ProductEntity productEntityWithoutPriority = TestObjectBuilder.buildAProductEntityWithoutPriority();
        ProductEntity productEntityWithPriority = TestObjectBuilder.buildAProductEntityWithPriority();
        List<ProductEntity> productEntities = new ArrayList<>();
        Product product = TestObjectBuilder.buildAProductWithPriority();
        productEntities.add(productEntityWithoutPriority);
        productEntities.add(productEntityWithPriority);

        when(productRepository.findByDateBetweenAndProductIdAndBrandId(getProductRequest.getDate(),
                getProductRequest.getProductId(),
                getProductRequest.getBrandId())).thenReturn(productEntities);

        Product productResponse = productPersistenceH2Adapter.findByDateAndProductIdAndBrandId(getProductRequest);

        assertEquals(product, productResponse);
    }

    @Test
    public void shouldThrowANotFoundException() {
        GetProductRequest getProductRequest = (GetProductRequest) TestObjectBuilder.buildGetProductRequest();
        List<ProductEntity> productEntities = new ArrayList<>();

        when(productRepository.findByDateBetweenAndProductIdAndBrandId(getProductRequest.getDate(),
                getProductRequest.getProductId(),
                getProductRequest.getBrandId())).thenReturn(productEntities);

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> productPersistenceH2Adapter.findByDateAndProductIdAndBrandId(getProductRequest));

        assertEquals("Product Not Found", notFoundException.getMessage());
    }


}
