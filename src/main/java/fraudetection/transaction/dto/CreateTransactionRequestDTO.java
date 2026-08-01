package fraudetection.transaction.dto;

import java.math.BigDecimal;

public record CreateTransactionRequestDTO(BigDecimal amount, String merchant, String country){
}