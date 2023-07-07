package ua.ithillel.bank.versioning.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ithillel.bank.versioning.currency.ApiCurrencyConverter;
import ua.ithillel.bank.versioning.reposytory.*;

import java.util.Currency;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    //@Autowired
    private final AccountRepository accountRepository;
    //@Autowired
    private final TransactionRepository transactionRepository;

    private final ApiCurrencyConverter apiCurrencyConverter;

//    @Autowired
//    public TransactionService(ApiCurrencyConverter apiCurrencyConverter) {
//        this.apiCurrencyConverter = apiCurrencyConverter;
//    }
    public TransactionDto getTransaction(String uid) {
        return transactionRepository.findByUid(uid).map(this::mapTransaction).orElseThrow();
    }

    @Transactional
    public TransactionDto transferBalance(TransactionDto transactionDto){

        Account fromAccount = accountRepository.findByIban(transactionDto.ibanFrom())
                .orElseThrow(() -> new AccountNotFoundException("From account not found"));

        Account toAccount = accountRepository.findByIban(transactionDto.ibanTo())
                .orElseThrow(() -> new AccountNotFoundException("To account not found"));

        String transferCurrency = transactionDto.amount().getCurrency();
        if (!fromAccount.getCurrency().equals(transferCurrency)) {
            throw new ValidationException("Invalid currencies for the transfer");
        }

        int transferAmountFrom = Integer.parseInt(transactionDto.amount().getValue());
        if (transferAmountFrom < 0 || transferAmountFrom > fromAccount.getBalance()) {
            throw new ValidationException("Insufficient balance in the account");
        }

        int transferAmountTo = transferAmountFrom;
        if (!toAccount.getCurrency().equals(transferCurrency)) {
            Currency currencyFrom = Currency.getInstance(fromAccount.getCurrency());
            Currency currencyTo = Currency.getInstance(toAccount.getCurrency());

            transferAmountTo = (int)apiCurrencyConverter.convert(currencyFrom, currencyTo, transferAmountFrom);
        }

        fromAccount.decreaseBalance(transferAmountFrom);
        toAccount.increaseBalance(transferAmountTo);

        Transaction newTransaction = Transaction.builder()
                .uid(UUID.randomUUID().toString())
                .ibanFrom(transactionDto.ibanFrom())
                .ibanTo(transactionDto.ibanTo())
                .quantity(Integer.parseInt(transactionDto.amount().getValue()))
                .currency(transactionDto.amount().getCurrency())
                .build();
        var transactionSaved = transactionRepository.save(newTransaction);

        return mapTransaction(transactionSaved);
    }

    private TransactionDto mapTransaction(Transaction transaction) {
        return TransactionDto.builder()
                .uid(transaction.getUid())
                .ibanFrom(transaction.getIbanFrom())
                .ibanTo(transaction.getIbanTo())
                .amount(new Amount(String.valueOf(transaction.getQuantity()), transaction.getCurrency()))
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
