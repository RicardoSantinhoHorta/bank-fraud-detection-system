package fraudetection.account.dto;

import java.math.BigDecimal;

public record AccountDetailsResponseDTO(String accountOwner, BigDecimal balance, String country) {
}
