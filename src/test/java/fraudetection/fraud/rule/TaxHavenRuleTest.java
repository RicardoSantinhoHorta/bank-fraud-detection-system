package fraudetection.fraud.rule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxHavenRuleTest {

    @Test
    void taxHavenRiskLevelNone1(){
        TaxHavenRiskLevel result = TaxHavenRule.determineTaxHavenRiskLevel("Portugal");
        assertEquals(TaxHavenRiskLevel.NONE, result);
    }

    @Test
    void taxHavenRiskLevelNone2(){
        TaxHavenRiskLevel result = TaxHavenRule.determineTaxHavenRiskLevel("Spain");
        assertEquals(TaxHavenRiskLevel.NONE, result);
    }

    @Test
    void taxHavenRiskLevelLow1(){
        TaxHavenRiskLevel result = TaxHavenRule.determineTaxHavenRiskLevel("Monaco");
        assertEquals(TaxHavenRiskLevel.LOW, result);
    }

    @Test
    void taxHavenRiskLevelLow2(){
        TaxHavenRiskLevel result = TaxHavenRule.determineTaxHavenRiskLevel("Andorra");
        assertEquals(TaxHavenRiskLevel.LOW, result);
    }

    @Test
    void taxHavenRiskLevelMedium1(){
        TaxHavenRiskLevel result = TaxHavenRule.determineTaxHavenRiskLevel("Luxembourg");
        assertEquals(TaxHavenRiskLevel.MEDIUM, result);
    }

    @Test
    void taxHavenRiskLevelMedium2(){
        TaxHavenRiskLevel result = TaxHavenRule.determineTaxHavenRiskLevel("Malta");
        assertEquals(TaxHavenRiskLevel.MEDIUM, result);
    }

    @Test
    void taxHavenRiskLevelHigh1(){
        TaxHavenRiskLevel result = TaxHavenRule.determineTaxHavenRiskLevel("Cayman Islands");
        assertEquals(TaxHavenRiskLevel.HIGH, result);
    }

    @Test
    void taxHavenRiskLevelHigh2(){
        TaxHavenRiskLevel result = TaxHavenRule.determineTaxHavenRiskLevel("Panama");
        assertEquals(TaxHavenRiskLevel.HIGH, result);
    }
}