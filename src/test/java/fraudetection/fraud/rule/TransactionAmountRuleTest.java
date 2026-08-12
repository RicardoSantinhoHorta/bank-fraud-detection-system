package fraudetection.fraud.rule;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionAmountRuleTest {


    @Test
    void EvaluateAmountLow1()
    {
        TransactionAmountLevel result =
                TransactionAmountRule.EvaluateMoneyAmountLevel(new BigDecimal(30));

        assertEquals(TransactionAmountLevel.LOW, result);
    }

    @Test
    void EvaluateAmountLow2()
    {
        TransactionAmountLevel result =
                TransactionAmountRule.EvaluateMoneyAmountLevel(new BigDecimal(499));

        assertEquals(TransactionAmountLevel.LOW, result);
    }

    @Test
    void EvaluateAmountMedium1()
    {
        TransactionAmountLevel result =
                TransactionAmountRule.EvaluateMoneyAmountLevel(new BigDecimal(500));

        assertEquals(TransactionAmountLevel.MEDIUM, result);
    }

    @Test
    void EvaluateAmountMedium2()
    {
        TransactionAmountLevel result =
                TransactionAmountRule.EvaluateMoneyAmountLevel(new BigDecimal(500));

        assertEquals(TransactionAmountLevel.MEDIUM, result);
    }

    @Test
    void EvaluateAmountHigh1()
    {
        TransactionAmountLevel result =
                TransactionAmountRule.EvaluateMoneyAmountLevel(new BigDecimal(1000));

        assertEquals(TransactionAmountLevel.HIGH, result);
    }

    @Test
    void EvaluateAmountHigh2()
    {
        TransactionAmountLevel result =
                TransactionAmountRule.EvaluateMoneyAmountLevel(new BigDecimal(3000));

        assertEquals(TransactionAmountLevel.HIGH, result);
    }
}