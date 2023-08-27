package projekat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private int transactionId;
    private int shipmentId;
    private TransactionStatusEnum transactionStatus;
    private Instant transactionDate;
    private double amount;
}
