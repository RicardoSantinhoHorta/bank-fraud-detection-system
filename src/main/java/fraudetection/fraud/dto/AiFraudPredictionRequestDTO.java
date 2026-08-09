package fraudetection.fraud.dto;

public record AiFraudPredictionRequestDTO(double amount,
                                          String transactionAmountLevel,
                                          String taxHavenRiskLevel,
                                          String transactionType) {
}
