package electronicCommerce.domain.service.impl;

import electronicCommerce.domain.config.Config;
import electronicCommerce.domain.constants.Constants;
import electronicCommerce.domain.exceptions.ValidationException;
import electronicCommerce.domain.models.Product;
import electronicCommerce.domain.models.impl.GetProductRequest;
import electronicCommerce.domain.ports.ProductPersistence;
import electronicCommerce.domain.services.impl.ProductServiceImpl;
import electronicCommerce.domain.utils.TestObjectBuilder;
import electronicCommerce.domain.validations.ValidationService;
import electronicCommerce.domain.validations.impl.ProductServiceValidationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.MockitoAnnotations.openMocks;

@ContextConfiguration(classes = Config.class)
public class ProductServiceImplTest {

    private ProductServiceImpl productServiceImpl;

    private ValidationService productServiceValidationServiceImpl = new ProductServiceValidationServiceImpl();

    @Mock
    private ProductPersistence productPersistenceH2Adapter;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        productServiceImpl = new ProductServiceImpl(productServiceValidationServiceImpl, productPersistenceH2Adapter);
    }

    @Test
    public void shouldReturnAProduct() {
        GetProductRequest getProductRequest = (GetProductRequest) TestObjectBuilder.buildGetProductRequest();

        Product product = TestObjectBuilder.buildAProductWithPriority();

        Mockito.when(productPersistenceH2Adapter.findByDateAndProductIdAndBrandId(getProductRequest)).thenReturn(product);
        Product productResult = productServiceImpl.readProductBy("2020-06-14-17.00.00",1,1);

        assertEquals(product, productResult);
    }

    @Test
    public void shouldThrowAValidationExceptionWhenDateIsInvalid() {

        ValidationException validationException = assertThrows(ValidationException.class,
                () -> productServiceImpl.readProductBy("2020-06-14-17",1,1));

        assertEquals(Constants.DATE_FORMAT_VALIDATION_MESSAGE, validationException.getMessage());
    }

    @Test
    public void shouldThrowAValidationExceptionWhenProductIdIsInvalid() {
        GetProductRequest getProductRequest = (GetProductRequest) TestObjectBuilder.buildGetProductRequest();

        ValidationException validationException = new ValidationException(Constants.PRODUCT_ID_CORRECT_VALUE_VALIDATION_MESSAGE);

        Mockito.when(productPersistenceH2Adapter.findByDateAndProductIdAndBrandId(getProductRequest)).thenThrow(validationException);
        ValidationException validationExceptionResponse = assertThrows(ValidationException.class,
                () -> productServiceImpl.readProductBy("2020-06-14-17.00.00",1,1));

        assertEquals(Constants.PRODUCT_ID_CORRECT_VALUE_VALIDATION_MESSAGE, validationExceptionResponse.getMessage());
    }

    @Test
    public void shouldThrowAValidationExceptionWhenBrandIdIsInvalid() {
        GetProductRequest getProductRequest = (GetProductRequest) TestObjectBuilder.buildGetProductRequest();

        ValidationException validationException = new ValidationException(Constants.BRAND_ID_CORRECT_VALUE_VALIDATION_MESSAGE);

        Mockito.when(productPersistenceH2Adapter.findByDateAndProductIdAndBrandId(getProductRequest)).thenThrow(validationException);
        ValidationException validationExceptionResponse = assertThrows(ValidationException.class,
                () -> productServiceImpl.readProductBy("2020-06-14-17.00.00",1,1));

        assertEquals(Constants.BRAND_ID_CORRECT_VALUE_VALIDATION_MESSAGE, validationExceptionResponse.getMessage());
    }
}