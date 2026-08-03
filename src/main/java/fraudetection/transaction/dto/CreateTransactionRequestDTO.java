package fraudetection.transaction.dto;

import java.math.BigDecimal;

public record CreateTransactionRequestDTO(Long senderAccountId,
                                          String senderCountry,
                                          Long receiverAccountId,
                                          String receiverCountry,
                                          BigDecimal amount) {
}