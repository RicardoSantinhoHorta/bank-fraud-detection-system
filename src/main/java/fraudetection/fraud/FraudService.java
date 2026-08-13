package fraudetection.fraud;
import fraudetection.fraud.rule.TransactionTypeRule;
import fraudetection.fraud.rule.TransactionType;
import fraudetection.transaction.Transaction;
import fraudetection.transaction.TransactionRiskLevel;
import fraudetection.transaction.TransactionState;
import org.springframework.stereotype.Service;

@Service
public class FraudService {

    private AiClient aiClient;

    public FraudService(AiClient aiClient) {
        this.aiClient = aiClient;
    }

    public void verifyTransaction(Transaction transaction) {

        double riskScoreAi = aiClient.getRiskScore(transaction);
        transaction.setRiskScore(riskScoreAi);
        transaction.setRiskLevel(determineRiskScore(riskScoreAi));
        transaction.setTransactionState(determineTransactionState(transaction.getRiskLevel()));

    }

    public TransactionRiskLevel determineRiskScore(double riskScoreAi){
        if (riskScoreAi < 0 || riskScoreAi > 1) {
            throw new IllegalArgumentException("Risk score must be between 0 and 1");
        }
        if (riskScoreAi < 0.3) {
            return TransactionRiskLevel.LOW;
        }
        if (riskScoreAi < 0.8) {
            return TransactionRiskLevel.MEDIUM;
        }
        return TransactionRiskLevel.HIGH;

    }

    public TransactionState determineTransactionState(TransactionRiskLevel riskLevel){
        if (riskLevel == TransactionRiskLevel.LOW || riskLevel == TransactionRiskLevel.MEDIUM) {
            return TransactionState.APPROVED;
        }
        return TransactionState.REJECTED;
    }
}
