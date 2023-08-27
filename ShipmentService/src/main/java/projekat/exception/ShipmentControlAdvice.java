package projekat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ShipmentControlAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ShipmentCustomException.class)
    public ResponseEntity<ErrorMessage> handleTransactionException(ShipmentCustomException exception) {
        ErrorMessage message = new ErrorMessage();
        message.setErrorMessage(exception.getMessage());
        message.setErrorCode(exception.getCode());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
