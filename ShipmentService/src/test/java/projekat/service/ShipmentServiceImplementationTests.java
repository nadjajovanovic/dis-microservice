package projekat.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ShipmentServiceImplementationTests {

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private TransactionService transactionService;

    @Mock
    private CargoService cargoService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    ShipmentService shipmentService = new ShipmentServiceImplementation();

    /*@Test
    void testWhenShipmentSuccess() {
        Shipment shipment = getMockShipment();
        when(shipmentRepository.findById(anyInt()))
                .thenReturn(Optional.of(shipment));

        when(restTemplate.getForObject(
                "http://CARGO-SERVICE/cargo/" + shipment.getCargoId(),
                CargoResponse.class
        )).thenReturn(getMockCargoResponse());

        when(restTemplate.getForObject(
                "http://TRANSACTION-SERVICE/transaction/" + shipment.getShipmentId(),
                TransactionResponse.class
        )).thenReturn(getMockTransactionResponse());

        final var shipmentResponse = shipmentService.getShipmentById(shipment.getShipmentId());


        verify(shipmentRepository, times(1)).findById(anyInt());
        verify(restTemplate, times(1)).getForObject(
                "http://CARGO-SERVICE/cargo/" + shipment.getCargoId(),
                CargoResponse.class);
        verify(restTemplate, times(1)).getForObject(
                "http://TRANSACTION-SERVICE/transaction/" + shipment.getShipmentId(),
                TransactionResponse.class);



        assertEquals(shipment.getShipmentId(), shipmentResponse.getShipmentId());
    }*/

    @Test
    void testWhenGetShipmentNotFound() {

        when(shipmentRepository.findById(anyInt()))
                .thenReturn(Optional.ofNullable(null));

        ShipmentCustomException exception =
                assertThrows(ShipmentCustomException.class,
                        () -> shipmentService.getShipmentById(1));
        assertEquals(HttpStatus.NOT_FOUND, exception.getCode());

        verify(shipmentRepository, times(1))
                .findById(anyInt());
    }

    @Test
    void testWhenPlaceShipmentSuccess() {
        Shipment shipment = getMockShipment();
        ShipmentRequest shipmentRequest = getMockShipmentRequest();

        when(shipmentRepository.save(any(Shipment.class)))
                .thenReturn(shipment);
        when(transactionService.addTransaction(any(TransactionRequest.class)))
                .thenReturn(new ResponseEntity<Void>(HttpStatus.OK));

        final var id = shipmentService.placeShipment(shipmentRequest);

        verify(shipmentRepository, times(2))
                .save(any());
        verify(transactionService, times(1))
                .addTransaction(any(TransactionRequest.class));
    }

    @Test
    void testWhenPlaceShipmentFailed() {

        Shipment shipment = getMockShipment();
        ShipmentRequest shipmentRequest = getMockShipmentRequest();

        when(shipmentRepository.save(any(Shipment.class)))
                .thenReturn(shipment);
        when(transactionService.addTransaction(any(TransactionRequest.class)))
                .thenThrow(new RuntimeException());

        final var s = shipmentService.placeShipment(shipmentRequest);

        verify(shipmentRepository, times(2))
                .save(any());
        verify(transactionService, times(1))
                .addTransaction(any(TransactionRequest.class));
    }


    private ShipmentRequest getMockShipmentRequest() {
        ShipmentRequest request = new ShipmentRequest();
        request.setCargoId(1);
        request.setNumberOfParts(10);
        request.setShipmentStatus("CREATED");
        request.setAmount(100);
        return request;
    }

    private TransactionResponse getMockTransactionResponse() {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionId(1);
        response.setShipmentId(2);
        response.setTransactionStatus(TransactionStatusEnum.SUCCESSFUL);
        response.setTransactionDate(Instant.now());
        response.setAmount(100);
        return response;
    }

    private CargoResponse getMockCargoResponse() {
        CargoResponse response = new CargoResponse();
        response.setCargoId(1);
        response.setMimeType("fragile");
        response.setPacking("glass");
        response.setDescription("description");
        return response;
    }

    private Shipment getMockShipment() {
        Shipment shipment = new Shipment();
        shipment.setShipmentId(1);
        shipment.setCargoId(1);
        shipment.setShipmentDate(Instant.now());
        shipment.setNumberOfParts(10);

        return shipment;
    }

}
