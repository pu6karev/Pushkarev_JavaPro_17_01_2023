package ua.ithillel.bank.externalIntegration;

import org.springframework.web.reactive.function.client.WebClient;
import ua.ithillel.bank.externalIntegration.model.ResponseApi;

import java.util.Currency;
import java.util.Map;
import java.util.stream.Collectors;

public class ApiCurrencyConverter implements CurrencyConverter {
    private final CurrencyConverterConfig currencyConverterConfig;
    private final WebClient webClient;

    public ApiCurrencyConverter(CurrencyConverterConfig currencyConverterConfig) {
        this.currencyConverterConfig = currencyConverterConfig;
        this.webClient = WebClient.builder().build();
    }

    @Override
    public double convert(Currency from, Currency to, double amount) {
        Map<String, Double> currencyMap = getMapFromApi();

        String key1 = from.toString();
        double rate1 = (currencyMap.containsKey(key1)) ? currencyMap.get(key1) : 0;

        String key2 = to.toString();
        double rate2 = (currencyMap.containsKey(key2)) ? currencyMap.get(key2) : 0;

        if(rate1 == 0 || rate2 == 0) {
            System.out.println("Currency is not found, try again.");
            return -1;
        }

        double result = (amount * rate2) / rate1;

        System.out.println(" key1=" + key1 + " rate1=" + rate1);
        System.out.println(" key2=" + key2 + " rate2=" + rate2 + " result=" + result);
        return result;
    }

    private Map<String, Double> getMapFromApi(){
        String url = currencyConverterConfig.getUrl()+currencyConverterConfig.getApikey();

        ResponseApi result = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ResponseApi.class)
                .block();

         // --- выборка в отдельный Map данных из ReponseData, которые содержат пару: валюта - курс
        return result.getData().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getValue()
                ));

    }
}
