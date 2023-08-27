package projekat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TransactionControlAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransactionCustomException.class)
    public ResponseEntity<ErrorMessage> handleTransactionException(TransactionCustomException exception) {
        ErrorMessage message = new ErrorMessage();
        message.setErrorMessage(exception.getMessage());
        message.setErrorCode(exception.getCode());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
