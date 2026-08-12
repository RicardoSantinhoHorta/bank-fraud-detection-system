package fraudetection.fraud.rule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTypeRuleTest {

    @Test
    void TransactionTypeDomestic1(){
        TransactionType result =
                TransactionTypeRule.determineTransactionType
                        ("Portugal", "Portugal");

        assertEquals(TransactionType.DOMESTIC, result);
    }

    @Test
    void TransactionTypeDomestic2(){
        TransactionType result =
                TransactionTypeRule.determineTransactionType
                        ("Spain", "Spain");

        assertEquals(TransactionType.DOMESTIC, result);
    }


    @Test
    void TransactionTypeInternational1(){
        TransactionType result =
                TransactionTypeRule.determineTransactionType
                        ("Portugal", "Spain");

        assertEquals(TransactionType.INTERNATIONAL, result);
    }

    @Test
    void TransactionTypeInternational2(){
        TransactionType result =
                TransactionTypeRule.determineTransactionType
                        ("Spain", "Portugal");

        assertEquals(TransactionType.INTERNATIONAL, result);
    }

    @Test
    void TransactionTypeInternational3(){
        TransactionType result =
                TransactionTypeRule.determineTransactionType
                        ("Monaco", "Brazil");

        assertEquals(TransactionType.INTERNATIONAL, result);
    }
}