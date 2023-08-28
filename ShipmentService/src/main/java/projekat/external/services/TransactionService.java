package projekat.external.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import projekat.exception.ShipmentCustomException;
import projekat.external.request.TransactionRequest;
import projekat.external.response.TransactionResponse;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "TRANSACTION-SERVICE/transaction")
public interface TransactionService {

    @PostMapping
    ResponseEntity<Void> addTransaction(TransactionRequest transactionRequest);

    default ResponseEntity<TransactionResponse> fallback(Exception e) {
        throw new ShipmentCustomException("Transaction service is not available", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
