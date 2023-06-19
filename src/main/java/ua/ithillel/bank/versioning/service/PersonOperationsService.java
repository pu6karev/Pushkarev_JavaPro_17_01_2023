package ua.ithillel.bank.versioning.service;

import org.springframework.stereotype.Service;
import ua.ithillel.bank.versioning.currency.CurrencyConverter;

import java.util.Currency;
import java.util.concurrent.CompletableFuture;

@Service
public class PersonOperationsService {
    private final CurrencyConverter currencyConverter;

    public PersonOperationsService(CurrencyConverter currencyConverter) {
        this.currencyConverter = currencyConverter;
    }

    public CompletableFuture<Double> convert(Currency from, Currency to, double amount) {
        return CompletableFuture.supplyAsync(() -> currencyConverter.convert(from, to, amount));
    }
}
