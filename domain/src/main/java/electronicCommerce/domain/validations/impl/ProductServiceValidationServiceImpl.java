package electronicCommerce.domain.validations.impl;

import electronicCommerce.domain.constants.Constants;
import electronicCommerce.domain.exceptions.ValidationException;
import electronicCommerce.domain.models.RequestDomain;
import electronicCommerce.domain.models.impl.GetProductRequest;
import electronicCommerce.domain.validations.ValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@Slf4j
public class ProductServiceValidationServiceImpl implements ValidationService {

    @Override
    public void validate(RequestDomain requestDomain)  {
        GetProductRequest getProductRequest = (GetProductRequest) requestDomain;

        try {
            log.info("Validating Request");
            Assert.isTrue(getProductRequest.getProductId() > 0, Constants.PRODUCT_ID_CORRECT_VALUE_VALIDATION_MESSAGE);
            Assert.isTrue(getProductRequest.getBrandId() > 0, Constants.BRAND_ID_CORRECT_VALUE_VALIDATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            log.warn("Validation Failed");
            throw new ValidationException(e.getMessage());
        }
    }
}
