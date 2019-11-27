package hermanos.Centro.Clinico.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceConflictException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            ResourceConflictException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
