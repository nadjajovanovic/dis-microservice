package projekat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekat.dto.TransactionStatusEnum;

import java.time.Instant;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;

    @Column(name="shipmentId")
    private int shipmentId;

    @Enumerated(EnumType.STRING)
    @Column(name="transactionStatus")
    private TransactionStatusEnum transactionStatus;

    @Column(name="transactionDate")
    private Instant transactionDate;

    @Column(name="amount")
    private double amount;
}
