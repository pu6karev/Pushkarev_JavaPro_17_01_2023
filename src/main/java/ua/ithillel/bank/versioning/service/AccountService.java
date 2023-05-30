package ua.ithillel.bank.versioning.service;

import org.springframework.stereotype.Service;
import ua.ithillel.bank.versioning.reposytory.Account;
import ua.ithillel.bank.versioning.reposytory.AccountRepository;
import ua.ithillel.bank.versioning.reposytory.Person;
import ua.ithillel.bank.versioning.reposytory.PersonRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    public AccountService(AccountRepository accountRepository, PersonRepository personRepository) {
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll().stream().toList();
    }

    public List<Account> getAccounts(String personUid) {
        Person person = personRepository.findByUid(personUid).orElseThrow();
        return accountRepository.findAll().stream()
                .filter(account -> account.getPersonId() == person.getId())
                .collect(Collectors.toList());
    }

    public Account getAccount(String iban) {
        return accountRepository.findByIban(iban).orElseThrow();
    }

    public Account createAccount(String personUid, Account account) {
        Person person = personRepository.findByUid(personUid).orElseThrow();
        long idPerson = person.getId();
        return accountRepository.save(Account.builder()
                .uid(UUID.randomUUID().toString())
                .iban(account.getIban())
                .balance(account.getBalance())
                .personId(idPerson)
                .build());
    }

    public Account updateAccount(String uid, Account account) {
        var accountToUpdate = accountRepository.findByUid(uid).orElseThrow();
        accountToUpdate.setIban(account.getIban());
        accountToUpdate.setBalance(account.getBalance());
        return accountRepository.save(accountToUpdate);
    }

    public void deleteAccount(String uid) {
        var accountToDelete = accountRepository.findByUid(uid).orElseThrow();
        accountRepository.delete(accountToDelete);
    }
}
