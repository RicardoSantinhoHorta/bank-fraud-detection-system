package fraudetection.fraud.dto;

public record FraudResultDTO(boolean suspicious, int riskScore, String reason) {
}