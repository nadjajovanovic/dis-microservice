package projekat.external.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekat.dto.TransactionStatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {
    private int shipmentId;
    private TransactionStatusEnum transactionStatus;
    private double amount;
}
