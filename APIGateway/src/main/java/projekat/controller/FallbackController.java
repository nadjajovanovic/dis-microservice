package projekat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/cargoServiceFallBack")
    public String cargoServiceFallBack() {
        return "Cargo Service is down!";
    }

    @GetMapping("/transactionServiceFallBack")
    public String transactionServiceFallBack() {
        return "Transaction Service is down!";
    }

    @GetMapping("/shipmentServiceFallBack")
    public String shipmentServiceFallBack() {
        return "Shipment Service is down!";
    }
}

