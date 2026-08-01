package fraudetection.transaction.dto;

import fraudetection.transaction.TransactionState;

import java.math.BigDecimal;

public record TransactionDetailsResponseDTO(String senderCountry,
                                            String receiverCountry,
                                            BigDecimal amount,
                                            TransactionState transactionState) {
}
