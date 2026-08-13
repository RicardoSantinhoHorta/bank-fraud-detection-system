package fraudetection.transaction;

import fraudetection.exception.InsufficientBalanceException;
import fraudetection.fraud.FraudService;
import fraudetection.fraud.rule.*;
import fraudetection.transaction.dto.CreateTransactionRequestDTO;
import fraudetection.transaction.dto.TransactionDetailsResponseDTO;

import fraudetection.account.AccountService;
import fraudetection.account.Account;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private FraudService fraudService;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountService accountService,
                              FraudService fraudService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.fraudService = fraudService;
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

    @Transactional//Atómico. Ou a função vai até ao fim ou não acontece
    public TransactionDetailsResponseDTO createTransaction(CreateTransactionRequestDTO request) {
        Account sender = accountService.findById(request.senderAccountId());
        Account receiver = accountService.findById(request.receiverAccountId());

        if(!accountService.hasSufficientBalance(sender, request.amount())){
            throw new InsufficientBalanceException();
        }

        TransactionAmountLevel transactionAmountLevel = getTransactionAmountLevel(request.amount());
        TransactionType transactionType = getTransactionType(sender.getCountry(), receiver.getCountry());
        TaxHavenRiskLevel taxHavenRiskLevel = getTaxHavenRiskLevel(sender.getCountry());


        //Campos a preencher
        Transaction transaction = new Transaction();

        transaction.setSenderAccountId(sender.getId());
        transaction.setReceiverAccountId(receiver.getId());

        transaction.setAmount(request.amount());
        transaction.setTransactionAmountLevel(transactionAmountLevel);

        transaction.setSenderCountry(sender.getCountry());
        transaction.setReceiverCountry(receiver.getCountry());
        transaction.setTaxHavenRiskLevel(taxHavenRiskLevel);

        transaction.setTransactionType(transactionType);

        transaction.setTimestamp(LocalDateTime.now());

        //Verifica se é fraude, dá set riskScore, riskLevel TransactionState
        fraudService.verifyTransaction(transaction);
        //Salvar no repositório
        transactionRepository.save(transaction);

        transferFunds(sender, receiver, transaction.getAmount(), transaction.getTransactionState());




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

    private TransactionAmountLevel getTransactionAmountLevel(BigDecimal amount) {
        return TransactionAmountRule.EvaluateMoneyAmountLevel(amount);
    }

    private TaxHavenRiskLevel getTaxHavenRiskLevel(String senderCountry) {
        return TaxHavenRule.determineTaxHavenRiskLevel(senderCountry);
    }

    private void transferFunds(Account sender, Account receiver, BigDecimal amount, TransactionState transactionState) {
        if(transactionState == TransactionState.REJECTED) {
            return;
        }
        accountService.withdrawFunds(sender, amount);
        accountService.depositFunds(receiver, amount);
    }


}