package ua.ithillel.bank.externalIntegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Currency;

@SpringBootApplication
public class CurrencyApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(CurrencyApplication.class, args);
        CurrencyConverter currencyConverter = context.getBean(CurrencyConverter.class);

        Currency usd = Currency.getInstance("USD");
        Currency uah = Currency.getInstance("UAH");
        System.out.println(currencyConverter.convert(usd, uah, 100));
        System.out.println(currencyConverter.convert(uah, usd, 100));
    }
}
