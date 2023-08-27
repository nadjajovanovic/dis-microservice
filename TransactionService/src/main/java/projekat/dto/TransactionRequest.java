package projekat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequest {
    private int shipmentId;
    private TransactionStatusEnum transactionStatus;
    private double amount;
}
