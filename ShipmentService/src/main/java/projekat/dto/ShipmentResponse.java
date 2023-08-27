package projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentResponse {

    private int shipmentId;
    private Instant shipmentDate;
    private int numberOfParts;
    private String shipmentStatus;
    private CargoDetails cargoDetails;
    private TransactionDetails transactionDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CargoDetails {
        private int cargoId;
        private String mimeType;
        private String packing;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TransactionDetails {
        private int transactionId;
        private TransactionStatusEnum transactionStatus;
        private Instant transactionDate;
        private double amount;
    }
}
