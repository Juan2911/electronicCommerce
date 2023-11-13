package electronicCommerce.domain.validations.impl;

import electronicCommerce.domain.config.Config;
import electronicCommerce.domain.constants.Constants;
import electronicCommerce.domain.exceptions.ValidationException;
import electronicCommerce.domain.models.impl.GetProductRequest;
import electronicCommerce.domain.utils.TestObjectBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = Config.class)
public class ProductServiceValidationServiceImplTest {

    private ProductServiceValidationServiceImpl productServiceValidationService;

    @BeforeEach
    public void setUp() {
        productServiceValidationService = new ProductServiceValidationServiceImpl();
    }

    @Test
    public void shouldThrowAValidationExceptionWhenProductIdIsInvalid() {
        GetProductRequest getProductRequest = (GetProductRequest) TestObjectBuilder.buildGetProductRequest();
        getProductRequest.setProductId(0);

        ValidationException validationExceptionResponse = assertThrows(ValidationException.class,
                () -> productServiceValidationService.validate(getProductRequest));

        assertEquals(Constants.PRODUCT_ID_CORRECT_VALUE_VALIDATION_MESSAGE, validationExceptionResponse.getMessage());
    }

    @Test
    public void shouldThrowAValidationExceptionWhenBrandIdIsInvalid() {
        GetProductRequest getProductRequest = (GetProductRequest) TestObjectBuilder.buildGetProductRequest();
        getProductRequest.setBrandId(0);

        ValidationException validationExceptionResponse = assertThrows(ValidationException.class,
                () -> productServiceValidationService.validate(getProductRequest));

        assertEquals(Constants.BRAND_ID_CORRECT_VALUE_VALIDATION_MESSAGE, validationExceptionResponse.getMessage());
    }
}
