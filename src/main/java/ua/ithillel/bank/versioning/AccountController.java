package ua.ithillel.bank.versioning;

import org.springframework.web.bind.annotation.*;
import ua.ithillel.bank.versioning.reposytory.Account;
import ua.ithillel.bank.versioning.service.AccountService;

import java.util.List;

@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/api/accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/api/persons/{uid}/accounts")
    public List<Account> getUidAccounts(@PathVariable("uid") String uid) {
        return accountService.getAccounts(uid);
    }

    @GetMapping("/api/accounts/{accountIban}")
    public Account getAccountByIBan(@PathVariable("accountIban") String accountIban) {
        return accountService.getAccount(accountIban);
    }

    @PostMapping("/api/persons/{uid}/accounts")
    public Account createAccount(@PathVariable("uid") String uid, @RequestBody Account account) {
        return accountService.createAccount(uid, account);
    }

    @PutMapping("/api/accounts/{uid}")
    public Account updatePerson(@PathVariable("uid") String uid, @RequestBody Account account) {
        return accountService.updateAccount(uid, account);
    }

    @DeleteMapping("/api/accounts/{uid}")
    public void deleteAccount(@PathVariable("uid") String uid) {
        accountService.deleteAccount(uid);
    }
}