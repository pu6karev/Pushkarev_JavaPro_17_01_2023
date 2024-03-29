package ua.ithillel.bank.versioning.currency;

import lombok.extern.slf4j.Slf4j;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DummyCurrencyConverter implements CurrencyConverter {
    Map<String, Double> currencyMap = new HashMap<>();

    DummyCurrencyConverter(){
        currencyMap.put("USD", 1.0);
        currencyMap.put("EUR", 0.93);
        currencyMap.put("UAH", 36.91);
    }

    @Override
    public double convert(Currency from, Currency to, double amount) {

        String key1 = from.toString();
        double rate1 = (currencyMap.containsKey(key1)) ? currencyMap.get(key1) : 0;

        String key2 = to.toString();
        double rate2 = (currencyMap.containsKey(key2)) ? currencyMap.get(key2) : 0;

        if(rate1 == 0 || rate2 == 0){
            log.info("Currency is not found, try again.");
            return -1;
        }

        double result = (amount * rate2) / rate1;

        log.info("key1={} rate1={}", key1, rate1);
        log.info("key2={} rate2={} result={}", key2, rate2, result);
        return result;
    }
}
