package projekat.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import projekat.dto.TransactionRequest;
import projekat.dto.TransactionResponse;
import projekat.entity.Transaction;
import projekat.exception.TransactionCustomException;
import projekat.repository.TransactionRepository;

import java.time.Instant;
import java.util.Date;

@Service
@Log4j2
public class TransactionServiceImplementation implements TransactionService{

    @Autowired
    private TransactionRepository repository;

    @Override
    public Transaction addTransaction(TransactionRequest transactionRequest) {
        log.info("Adding transaction");

        Transaction transaction = new Transaction();
        transaction.setShipmentId(transactionRequest.getShipmentId());
        transaction.setTransactionStatus(transactionRequest.getTransactionStatus());
        transaction.setTransactionDate(Instant.now());
        transaction.setAmount(transactionRequest.getAmount());

        repository.save(transaction);
        log.info("Transaction created");

        return transaction;
    }

    @Override
    public TransactionResponse getTransactionByShipment(int id) {
        log.info("Getting transaction details for the shipment id: {}", id);

        if (!repository.existsById(Integer.valueOf(id))) {
            throw new TransactionCustomException("Shipment with that id does not exist", HttpStatus.NOT_FOUND);
        }

        Transaction transaction = repository.findByShipmentId(Integer.valueOf(id));

        TransactionResponse response = new TransactionResponse();

        response.setTransactionId(transaction.getTransactionId());
        //response.setShipmentId(transaction.getShipmentId());
        response.setTransactionStatus(transaction.getTransactionStatus());
        response.setTransactionDate(transaction.getTransactionDate());
        response.setAmount(transaction.getAmount());

        return response;
    }
}
