package fraudetection.account;

import fraudetection.account.dto.AccountDetailsResponseDTO;
import fraudetection.account.dto.CreateAccountRequestDTO;
import fraudetection.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //Usada no TransactionService
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public AccountDetailsResponseDTO getAccountDetailsById(Long id) {
        Account account = findById(id);

        return new AccountDetailsResponseDTO(
                account.getAccountOwner(),
                account.getBalance(),
                account.getCountry()
        );
    }

    public AccountDetailsResponseDTO createAccount(CreateAccountRequestDTO request) {
        Account account = new Account();

        //Não é necessário fazer setId(). O PostgreSQL que gera automaticamente
        account.setAccountOwner(request.accountOwner());
        account.setCountry(request.country());
        account.setBalance(BigDecimal.ZERO);

        accountRepository.save(account);

        return new AccountDetailsResponseDTO(
                account.getAccountOwner(),
                account.getBalance(),
                account.getCountry()
        );
    }
}
