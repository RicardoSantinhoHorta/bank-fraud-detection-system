package fraudetection.fraud.rule;

import fraudetection.transaction.Transaction;

import java.math.BigDecimal;

public class TransactionAmountRule {

    private static final BigDecimal LOW_LIMIT = BigDecimal.valueOf(500);
    private static final BigDecimal MEDIUM_LIMIT = BigDecimal.valueOf(1000);

    public TransactionAmountLevel EvaluateMoneyAmountLevel(BigDecimal moneyAmount) {
        if (moneyAmount.compareTo(LOW_LIMIT) < 0) {
            return TransactionAmountLevel.LOW;
        }
        if (moneyAmount.compareTo(MEDIUM_LIMIT) < 0) {
            return TransactionAmountLevel.MEDIUM;
        }
        else{
            return TransactionAmountLevel.HIGH;
        }
    }
}