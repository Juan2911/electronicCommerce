package electronicCommerce.domain.validations;

import electronicCommerce.domain.models.RequestDomain;

public interface ValidationService {
    void validate(RequestDomain requestDomain);
}
