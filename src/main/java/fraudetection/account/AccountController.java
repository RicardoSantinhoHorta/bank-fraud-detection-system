package fraudetection.account;

import fraudetection.account.dto.AccountDetailsResponseDTO;
import fraudetection.account.dto.CreateAccountRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{id}")
    public AccountDetailsResponseDTO findById(@PathVariable Long id) {
        return accountService.getAccountDetailsById(id);
    }

    @PostMapping("/accounts")
    public AccountDetailsResponseDTO createAccount(@RequestBody CreateAccountRequestDTO request){
        return accountService.createAccount(request);
    }
}
