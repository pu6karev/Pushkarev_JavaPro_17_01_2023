package ua.ithillel.bank.versioning.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ithillel.bank.versioning.reposytory.*;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final AccountRepository accountRepository;

    @Transactional
    public TransactionDto transferBalance(TransactionDto transactionDto){

        Account fromAccount = accountRepository.findByIban(transactionDto.ibanFrom())
                .orElseThrow(() -> new AccountNotFoundException("From account not found"));

        Account toAccount = accountRepository.findByIban(transactionDto.ibanTo())
                .orElseThrow(() -> new AccountNotFoundException("To account not found"));

        String transferCurrency = transactionDto.amount().getCurrency();
        if (!fromAccount.getCurrency().equals(transferCurrency) || !toAccount.getCurrency().equals(transferCurrency)) {
            throw new ValidationException("Invalid currencies for the transfer");
        }

        int transferAmount = Integer.parseInt(transactionDto.amount().getValue());
        if (transferAmount < 0 || transferAmount > fromAccount.getBalance()) {
            throw new ValidationException("Insufficient balance in the account");
        }

        fromAccount.decreaseBalance(transferAmount);
        toAccount.increaseBalance(transferAmount);

        Transaction newTransaction = Transaction.builder()
                .uid(UUID.randomUUID().toString())
                .ibanFrom(transactionDto.ibanFrom())
                .ibanTo(transactionDto.ibanTo())
                .value(Integer.parseInt(transactionDto.amount().getValue()))
                .currency(transactionDto.amount().getCurrency())
                .build();
        //var transactionSaved = transactionRepository.save(newTransaction);
        return mapTransaction(newTransaction);
    }

    private TransactionDto mapTransaction(Transaction transaction) {
        return TransactionDto.builder()
                .ibanFrom(transaction.getIbanFrom())
                .ibanTo(transaction.getIbanTo())
                .amount(new Amount(String.valueOf(transaction.getValue()), transaction.getCurrency()))
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
