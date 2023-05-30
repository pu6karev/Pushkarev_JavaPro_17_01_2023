package ua.ithillel.bank.versioning.service;

import org.springframework.stereotype.Service;
import ua.ithillel.bank.versioning.reposytory.Account;
import ua.ithillel.bank.versioning.reposytory.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll().stream().toList();
    }

    public List<Account> getAccounts(String uid) {
        return accountRepository.findAll().stream()
                .filter(account -> account.getUid().equals(uid))
                .collect(Collectors.toList());
    }

    public Account getAccount(String iban) {
        return accountRepository.findByIban(iban).orElseThrow();
    }

    public Account createAccount(String uid, Account account) {
        account.setUid(uid);
        return accountRepository.save(account);
    }

    public Account updateAccount(String iban, Account account) {
        var accountToUpdate = accountRepository.findByIban(iban).orElseThrow();
        accountToUpdate.setUid(accountToUpdate.getUid());
        accountToUpdate.setBalance(account.getBalance());
        return accountRepository.save(accountToUpdate);
    }
//    public Account updateAccount(String uid, Account account) {
//        var accountToUpdate = accountRepository.findByUid(uid).orElseThrow();
//        accountToUpdate.setUid(uid);
//        accountToUpdate.setIban(account.getIban());
//        accountToUpdate.setBalance(account.getBalance());
//        return accountRepository.save(accountToUpdate);
//    }

    public void deleteAccount(String accountIban) {
        var accountToDelete = accountRepository.findByIban(accountIban).orElseThrow();
        accountRepository.delete(accountToDelete);
    }
}
