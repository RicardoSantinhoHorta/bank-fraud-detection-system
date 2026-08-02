package fraudetection.transaction;

import fraudetection.transaction.TransactionService;
import fraudetection.transaction.dto.CreateTransactionRequestDTO;
import fraudetection.transaction.dto.TransactionDetailsResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions/{id}")
    public TransactionDetailsResponseDTO getTransactionDetails(@PathVariable Long id) {
        return transactionService.getTransactionDetailsById(id);
    }

    @PostMapping("/transactions")
    public TransactionDetailsResponseDTO createTransaction(@RequestBody CreateTransactionRequestDTO request) {
        return transactionService.createTransaction(request);
    }

}