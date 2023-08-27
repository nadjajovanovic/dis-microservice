package projekat.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class TransactionCustomException extends RuntimeException{
    private HttpStatus code;

    public TransactionCustomException(String message, HttpStatus code){
        super(message);
        this.code = code;
    }
}
