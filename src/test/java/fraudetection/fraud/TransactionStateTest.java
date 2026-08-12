package fraudetection.fraud;

import fraudetection.transaction.TransactionRiskLevel;
import fraudetection.transaction.TransactionState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FraudServiceTest {
    FraudService fraudService = new FraudService(new AiClient());

    @Test
    void determineTransactionStateApproved1(){
        TransactionState result = fraudService.determineTransactionState(TransactionRiskLevel.LOW);
        assertEquals(TransactionState.APPROVED, result);
    }

    @Test
    void determineTransactionStateApproved2(){
        TransactionState result = fraudService.determineTransactionState(TransactionRiskLevel.MEDIUM);
        assertEquals(TransactionState.APPROVED, result);
    }

    @Test
    void determineTransactionStateRejected1(){
        TransactionState result = fraudService.determineTransactionState(TransactionRiskLevel.HIGH);
        assertEquals(TransactionState.REJECTED, result);
    }

}