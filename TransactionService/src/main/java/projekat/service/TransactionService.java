package projekat.service;

import projekat.dto.TransactionRequest;
import projekat.dto.TransactionResponse;
import projekat.entity.Transaction;

import java.util.Collection;

public interface TransactionService {
    Transaction addTransaction(TransactionRequest transactionRequest);
    TransactionResponse getTransactionByShipment(int id);
}
