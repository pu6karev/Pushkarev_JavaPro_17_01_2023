package ua.ithillel.bank.versioning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ua.ithillel.bank.versioning.currency.CurrencyConverter;

import java.util.Currency;

@SpringBootApplication
@EnableJpaAuditing
public class BankApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(BankApplication.class, args);

        CurrencyConverter currencyConverter = context.getBean(CurrencyConverter.class);
        Currency usd = Currency.getInstance("USD");
        Currency uah = Currency.getInstance("UAH");
        System.out.println(currencyConverter.convert(usd, uah, 100));
        System.out.println(currencyConverter.convert(uah, usd, 100));

    }
}
