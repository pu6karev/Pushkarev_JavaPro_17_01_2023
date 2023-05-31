package ua.ithillel.bank.versioning;

import org.springframework.web.bind.annotation.*;
import ua.ithillel.bank.versioning.service.AccountDto;
import ua.ithillel.bank.versioning.service.AccountService;

import java.util.List;

@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/api/accounts")
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/api/persons/{uid}/accounts")
    public List<AccountDto> getUidAccounts(@PathVariable("uid") String personUid) {
        return accountService.getAccounts(personUid);
    }

    @GetMapping("/api/accounts/{uid}")
    public AccountDto getAccountByIBan(@PathVariable("uid") String uid) {
        return accountService.getAccount(uid);
    }

    @PostMapping("/api/persons/{uid}/accounts")
    public AccountDto createAccount(@PathVariable("uid") String personUid, @RequestBody AccountDto account) {
        return accountService.createAccount(personUid, account);
    }

    @PutMapping("/api/accounts/{uid}")
    public AccountDto updatePerson(@PathVariable("uid") String uid, @RequestBody AccountDto account) {
        return accountService.updateAccount(uid, account);
    }

    @DeleteMapping("/api/accounts/{uid}")
    public void deleteAccount(@PathVariable("uid") String uid) {
        accountService.deleteAccount(uid);
    }
}