package fraudetection.transaction.dto;

import fraudetection.transaction.TransactionState;

import java.math.BigDecimal;

public record TransactionDetailsResponseDTO(Long senderAccountId,
                                            String senderCountry,
                                            Long receiverAccountId,
                                            String receiverCountry,
                                            BigDecimal amount,
                                            TransactionState transactionState) {
}
