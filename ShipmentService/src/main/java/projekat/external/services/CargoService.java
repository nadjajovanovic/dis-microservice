package projekat.external.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import projekat.exception.ShipmentCustomException;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "CARGO-SERVICE/cargo")
public interface CargoService {

    default ResponseEntity<Void> fallback(Exception e) {
        throw new ShipmentCustomException("Cargo service is not available", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
