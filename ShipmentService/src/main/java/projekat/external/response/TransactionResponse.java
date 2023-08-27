package projekat.external.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekat.dto.TransactionStatusEnum;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private int transactionId;
    private int shipmentId;
    private TransactionStatusEnum transactionStatus;
    private Instant transactionDate;
    private double amount;
}
