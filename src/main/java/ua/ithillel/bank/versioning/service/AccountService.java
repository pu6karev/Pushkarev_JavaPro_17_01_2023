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

    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::mapAccount)
                .toList();
    }

    public List<AccountDto> getAccounts(String personUid) {
        Person person = personRepository.findByUid(personUid).orElseThrow();
        return accountRepository.findAll().stream()
                .map(this::mapAccount)
                .filter(account -> account.personId() == person.getId())
                .collect(Collectors.toList());
    }

    public AccountDto getAccount(String uid) {
        return accountRepository.findByUid(uid).map(this::mapAccount).orElseThrow();
    }

    public AccountDto createAccount(String personUid, AccountDto account) {
        Person person = personRepository.findByUid(personUid).orElseThrow();
        long idPerson = person.getId();

        Account newAccount = Account.builder()
                .uid(UUID.randomUUID().toString())
                .iban(account.iban())
                .balance(account.balance())
                .personId(idPerson)
                .build();
        accountRepository.save(newAccount);

        return mapAccount(newAccount);
    }

    public AccountDto updateAccount(String uid, AccountDto account) {
        var accountToUpdate = accountRepository.findByUid(uid).orElseThrow();
        accountToUpdate.setIban(account.iban());
        accountToUpdate.setBalance(account.balance());
        return mapAccount(accountRepository.save(accountToUpdate));
    }

    public void deleteAccount(String uid) {
        var accountToDelete = accountRepository.findByUid(uid).orElseThrow();
        accountRepository.delete(accountToDelete);
    }

    private AccountDto mapAccount(Account account) {
        return AccountDto.builder()
                .uid(account.getUid())
                .iban(account.getIban())
                .balance(account.getBalance())
                .personId(account.getPersonId())
                .build();
    }
}
