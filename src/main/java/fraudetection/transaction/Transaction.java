package fraudetection.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor //Para criar um construtor vazio para o Hibernate não dar erro
@Table(name = "transactions")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long SenderAccountId;
    private Long ReceiverAccountId;
    private BigDecimal amount;
    private String senderCountry; //O ideal seria ser o código tipo TP, ES, FR e não uma string
    private String ReceiverCountry;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private TransactionState transactionState;

    @Enumerated(EnumType.STRING)
    private TransactionRiskLevel riskLevel;

    private double riskScore;

}