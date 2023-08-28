package projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentRequest {

    private int cargoId;
    private int numberOfParts;
    private String shipmentStatus;
    private TransactionStatusEnum transactionStatusEnum;
    private int amount;
}
