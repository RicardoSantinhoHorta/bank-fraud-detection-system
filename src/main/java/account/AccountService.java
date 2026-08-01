package account;

public class AccountService {

    private final   AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }
}
