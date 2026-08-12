package fraudetection.transaction;

import fraudetection.account.Account;
import fraudetection.fraud.AiClient;
import fraudetection.fraud.FraudService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRiskLevelTest {
    FraudService fraudService = new FraudService(new AiClient());

    @Test
    void TransactionRiskLevelLow1() {
        TransactionRiskLevel result = fraudService.determineRiskScore(0);
        assertEquals(TransactionRiskLevel.LOW, result);
    }

    @Test
    void TransactionRiskLevelLow2() {
        TransactionRiskLevel result = fraudService.determineRiskScore(0.15);
        assertEquals(TransactionRiskLevel.LOW, result);
    }

    @Test
    void TransactionRiskLevelLow3() {
        TransactionRiskLevel result = fraudService.determineRiskScore(0.29);
        assertEquals(TransactionRiskLevel.LOW, result);
    }

    @Test
    void TransactionRiskLevelMedium1() {
        TransactionRiskLevel result = fraudService.determineRiskScore(0.30);
        assertEquals(TransactionRiskLevel.MEDIUM, result);
    }

    @Test
    void TransactionRiskLevelMedium2() {
        TransactionRiskLevel result = fraudService.determineRiskScore(0.79);
        assertEquals(TransactionRiskLevel.MEDIUM, result);
    }

    @Test
    void TransactionRiskLevelHigh1() {
        TransactionRiskLevel result = fraudService.determineRiskScore(0.8);
        assertEquals(TransactionRiskLevel.HIGH, result);
    }

    @Test
    void TransactionRiskLevelHigh2() {
        TransactionRiskLevel result = fraudService.determineRiskScore(1);
        assertEquals(TransactionRiskLevel.HIGH, result);
    }
}