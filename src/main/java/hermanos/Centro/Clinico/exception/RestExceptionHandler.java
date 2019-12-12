package hermanos.Centro.Clinico.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return  new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(AccountNotActivatedException.class)
    protected ResponseEntity<Object> handleAccountLocked(
            AccountNotActivatedException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
