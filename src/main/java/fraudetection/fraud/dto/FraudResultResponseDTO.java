package fraudetection.fraud.dto;

public record FraudResultResponseDTO(boolean suspicious, int riskScore, String reason) {
}