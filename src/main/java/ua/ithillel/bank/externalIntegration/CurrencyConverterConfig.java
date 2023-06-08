package ua.ithillel.bank.externalIntegration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("currency.converter")
public class CurrencyConverterConfig {
    private String url;
    private String apikey;
}
