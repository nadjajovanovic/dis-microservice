package projekat.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekat.dto.ShipmentRequest;
import projekat.dto.ShipmentResponse;
import projekat.entity.Shipment;
import projekat.service.ShipmentService;

@RestController
@RequestMapping("/shipment")
@Log4j2
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping("/place-shipment")
    public ResponseEntity<Shipment> placeShipment(@RequestBody ShipmentRequest shipmentRequest) {
        final var shipment = shipmentService.placeShipment(shipmentRequest);
        return new ResponseEntity<>(shipment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponse> getShipmentDetails(@PathVariable Integer id) {
        ShipmentResponse shipmentResponse = shipmentService.getShipmentById(id);
        return new ResponseEntity<>(shipmentResponse, HttpStatus.OK);
    }
}
