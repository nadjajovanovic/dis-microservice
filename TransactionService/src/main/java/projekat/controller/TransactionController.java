package projekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekat.dto.TransactionRequest;
import projekat.dto.TransactionResponse;
import projekat.entity.Transaction;
import projekat.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/add")
    public ResponseEntity<Transaction> addTransaction(@RequestBody TransactionRequest request) {
        final var transaction = service.addTransaction(request);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionByShipment(@PathVariable Integer id) {
        final var transactionByShipment = service.getTransactionByShipment(id);
        return new ResponseEntity<>(transactionByShipment, HttpStatus.OK);
    }

}
