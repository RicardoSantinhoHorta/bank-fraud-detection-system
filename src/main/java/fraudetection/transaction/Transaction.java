package fraudetection.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import fraudetection.fraud.rule.TaxHavenRiskLevel;
import fraudetection.fraud.rule.TransactionAmountLevel;
import fraudetection.fraud.rule.TransactionType;
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

    private Long senderAccountId;
    private Long receiverAccountId;

    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionAmountLevel transactionAmountLevel;

    private String senderCountry; //O ideal seria ser o código tipo TP, ES, FR e não uma string
    private String receiverCountry;
    @Enumerated(EnumType.STRING)
    private TaxHavenRiskLevel taxHavenRiskLevel; //Associado ao senderCountry

    private TransactionType transactionType;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private TransactionState transactionState;

    @Enumerated(EnumType.STRING)
    private TransactionRiskLevel riskLevel;

    private double riskScore;


}