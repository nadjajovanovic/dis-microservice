package projekat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekat.dto.TransactionStatusEnum;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int shipmentId;

    @Column(name = "cargoId")
    private int cargoId;

    @Column(name = "shipmentDate")
    private Instant shipmentDate;

    @Column(name = "numberOfParts")
    private int numberOfParts;

    @Column(name="shipmentStatus")
    private String shipmentStatus;

    @Column(name="amount")
    private int amount;

    @Column(name="transactionStatus")
    private TransactionStatusEnum transactionStatus;
}
