package fraudetection.transaction;

import fraudetection.fraud.rule.TransactionType;
import fraudetection.fraud.rule.TransactionTypeRule;
import fraudetection.transaction.dto.CreateTransactionRequestDTO;
import fraudetection.transaction.dto.TransactionDetailsResponseDTO;

import fraudetection.account.AccountService;
import fraudetection.account.Account;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    public TransactionDetailsResponseDTO getTransactionDetailsById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow();

        return new TransactionDetailsResponseDTO(
                transaction.getSenderAccountId(),
                transaction.getSenderCountry(),
                transaction.getReceiverAccountId(),
                transaction.getReceiverCountry(),
                transaction.getAmount(),
                transaction.getTransactionState()
        );
    }

    public TransactionDetailsResponseDTO createTransaction(CreateTransactionRequestDTO request) {
        Account sender = accountService.findById(request.senderAccountId());
        Account receiver = accountService.findById(request.receiverAccountId());
        TransactionType transactionType =
                getTransactionType(request.senderCountry(), request.receiverCountry());


        //Campos a preencher
        Transaction transaction = new Transaction();

        transaction.setSenderAccountId(sender.getId());
        transaction.setReceiverAccountId(receiver.getId());

        transaction.setAmount(request.amount());

        transaction.setSenderCountry(sender.getCountry());
        transaction.setReceiverCountry(receiver.getCountry());

        transaction.setTransactionType(transactionType);

        transaction.setTimestamp(LocalDateTime.now());

        transaction.setTransactionState(TransactionState.APPROVED);

        transaction.setRiskLevel(TransactionRiskLevel.UNKNOWN);

        transaction.setRiskScore(0);
        //Salvar no repositório
        transactionRepository.save(transaction);

        return new TransactionDetailsResponseDTO(
                transaction.getSenderAccountId(),
                transaction.getSenderCountry(),
                transaction.getReceiverAccountId(),
                transaction.getReceiverCountry(),
                transaction.getAmount(),
                transaction.getTransactionState()
        );
    }

    private TransactionType getTransactionType(String  senderCountry, String receiverCountry) {
        return TransactionTypeRule.determineTransactionType(senderCountry, receiverCountry);
    }
}