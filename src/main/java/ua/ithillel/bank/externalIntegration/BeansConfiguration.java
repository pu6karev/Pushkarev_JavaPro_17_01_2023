package ua.ithillel.bank.externalIntegration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
    @Configuration
    @ConditionalOnProperty(name = "currency.converter.provider", havingValue = "currencyapi")
    public static class ApiCurrencyConverterConfiguration{
        @Bean
        public CurrencyConverter currencyConverter(CurrencyConverterConfig currencyConverterConfig){
            return new ApiCurrencyConverter(currencyConverterConfig);
        }
    }

    @Configuration
    @ConditionalOnProperty(name = "currency.converter.provider", havingValue = "dummy")
    public static class DummyCurrencyConverterConfiguration{
        @Bean
        public CurrencyConverter currencyConverter(){
            return new DummyCurrencyConverter();
        }
    }
}
