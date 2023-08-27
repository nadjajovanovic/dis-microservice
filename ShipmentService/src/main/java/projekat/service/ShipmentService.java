package projekat.service;

import projekat.dto.ShipmentRequest;
import projekat.dto.ShipmentResponse;
import projekat.entity.Shipment;

public interface ShipmentService {
    Shipment placeShipment(ShipmentRequest shipmentRequest);
    ShipmentResponse getShipmentById(int id);
}
