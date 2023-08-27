package projekat.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import projekat.dto.ShipmentRequest;
import projekat.dto.ShipmentResponse;
import projekat.dto.TransactionStatusEnum;
import projekat.entity.Shipment;
import projekat.exception.ShipmentCustomException;
import projekat.external.request.TransactionRequest;
import projekat.external.response.CargoResponse;
import projekat.external.response.TransactionResponse;
import projekat.external.services.CargoService;
import projekat.external.services.TransactionService;
import projekat.repository.ShipmentRepository;

import java.time.Instant;

@Service
@Log4j2
public class ShipmentServiceImplementation implements ShipmentService{

    @Autowired
    private ShipmentRepository repository;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Shipment placeShipment(ShipmentRequest shipmentRequest) {
        log.info("Placing shipment");

        Shipment shipment = new Shipment();
        shipment.setCargoId(shipmentRequest.getCargoId());
        shipment.setShipmentDate(Instant.now());
        shipment.setNumberOfParts(shipmentRequest.getNumberOfParts());
        shipment.setShipmentStatus(shipmentRequest.getShipmentStatus());
        shipment.setAmount(shipmentRequest.getAmount());

        log.info("Shipment created");
        repository.save(shipment);

        log.info("Calling Transaction service to pay for the shipment");

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setShipmentId(shipment.getShipmentId());
        transactionRequest.setTransactionStatus(shipmentRequest.getTransactionStatusEnum());
        transactionRequest.setAmount(shipmentRequest.getAmount());

        TransactionStatusEnum status;

        try {
            transactionService.addTransaction(transactionRequest);
            log.info("Transaction done successfully. Changing shipment status to successful");
            status = TransactionStatusEnum.SUCCESSFUL;
        } catch (Exception e) {
            log.error("Transaction failed. Changing shipment status to failed");
            status = TransactionStatusEnum.FAILED;
        }

        shipment.setTransactionStatus(status);
        repository.save(shipment);

        return shipment;
    }

    @Override
    public ShipmentResponse getShipmentById(int id) {
        log.info("Get shipment details for id: {}", id);

        Shipment shipment = repository.findById(id).orElseThrow(() -> new ShipmentCustomException("Given id is no found", HttpStatus.NOT_FOUND));

        log.info("Invoking Cargo service to fetch the cargo for id: {}", shipment.getCargoId());
        CargoResponse cargoResponse = restTemplate.getForObject("http://CARGO-SERVICE/cargo/id/" + shipment.getCargoId(), CargoResponse.class);

        log.info("Getting transaction information form the Transaction Service");
        TransactionResponse transactionResponse = restTemplate.getForObject("http://TRANSACTION-SERVICE/transaction/" + shipment.getShipmentId(), TransactionResponse.class);

        ShipmentResponse.CargoDetails cargoDetails = new ShipmentResponse.CargoDetails();
        cargoDetails.setPacking(cargoResponse.getPacking());
        cargoDetails.setMimeType(cargoResponse.getMimeType());
        cargoDetails.setDescription(cargoResponse.getDescription());
        cargoDetails.setCargoId(cargoResponse.getCargoId());

        ShipmentResponse.TransactionDetails transactionDetails = new ShipmentResponse.TransactionDetails();
        transactionDetails.setTransactionId(transactionResponse.getTransactionId());
        transactionDetails.setTransactionStatus(transactionResponse.getTransactionStatus());
        transactionDetails.setTransactionDate(transactionResponse.getTransactionDate());
        transactionDetails.setAmount(transactionResponse.getAmount());

        ShipmentResponse shipmentResponse = new ShipmentResponse();
        shipmentResponse.setShipmentId(shipment.getShipmentId());
        shipmentResponse.setShipmentDate(shipment.getShipmentDate());
        shipmentResponse.setNumberOfParts(shipment.getNumberOfParts());
        shipmentResponse.setShipmentStatus(shipment.getShipmentStatus());
        shipmentResponse.setCargoDetails(cargoDetails);
        shipmentResponse.setTransactionDetails(transactionDetails);

        return shipmentResponse;
    }
}
