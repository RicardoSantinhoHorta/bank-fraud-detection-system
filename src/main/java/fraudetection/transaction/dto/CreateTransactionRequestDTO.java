package fraudetection.transaction.dto;

import java.math.BigDecimal;

public record CreateTransactionRequestDTO(Long senderAccountId,
                                          Long receiverAccountId,
                                          BigDecimal amount) {
}