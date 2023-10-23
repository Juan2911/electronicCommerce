package electronicCommerce.rest.exceptionHandlers;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private int httpStatus;
    private String type;
    private String message;
    private String clazz;
    private String method;
    private int line;

}
