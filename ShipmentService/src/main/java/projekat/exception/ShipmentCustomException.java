package projekat.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ShipmentCustomException extends RuntimeException{
    private HttpStatus code;

    public ShipmentCustomException(String message, HttpStatus code){
        super(message);
        this.code = code;
    }
}
