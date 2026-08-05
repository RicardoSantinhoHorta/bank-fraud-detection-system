package fraudetection.fraud.rule;

import fraudetection.transaction.Transaction;

public class TransactionTypeRule {

    public static TransactionType determineTransactionType(String senderCountry, String receiverCountry){
        if (senderCountry.equals(receiverCountry)){
            return TransactionType.DOMESTIC;
        }
        return TransactionType.INTERNATIONAL;
    }
}
