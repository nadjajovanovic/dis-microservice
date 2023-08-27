package projekat.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException {
    private HttpStatus code;

    public CustomException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
