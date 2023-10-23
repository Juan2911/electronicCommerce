package electronicCommerce.domain.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String detail) {
        super(detail);
    }
}
