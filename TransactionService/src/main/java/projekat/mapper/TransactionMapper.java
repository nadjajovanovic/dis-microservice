package projekat.mapper;

import projekat.dto.TransactionResponse;
import projekat.entity.Transaction;

public class TransactionMapper {

    public static TransactionResponse map (Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionId(transaction.getTransactionId());
        response.setShipmentId(transaction.getShipmentId());
        response.setTransactionStatus(response.getTransactionStatus());
        response.setTransactionDate(transaction.getTransactionDate());
        response.setAmount(transaction.getAmount());

        return response;
    }
}
