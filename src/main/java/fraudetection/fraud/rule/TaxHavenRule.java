package fraudetection.fraud.rule;

public class TaxHavenRule{

    public static TaxHavenRiskLevel determineTaxHavenRiskLevel(String country){
        return switch (country) {
            case "Cayman Islands", "Panama" -> TaxHavenRiskLevel.HIGH;
            case "Luxembourg", "Malta" -> TaxHavenRiskLevel.MEDIUM;
            case "Monaco", "Andorra" -> TaxHavenRiskLevel.LOW;
            default -> TaxHavenRiskLevel.NONE;
        };
    }
}