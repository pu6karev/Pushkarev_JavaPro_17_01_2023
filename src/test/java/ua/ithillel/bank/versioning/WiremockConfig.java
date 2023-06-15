package ua.ithillel.bank.versioning;

import com.github.tomakehurst.wiremock.WireMockServer;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WiremockConfig {

    private final static WireMockServer wireMockServer = new WireMockServer(8089);
    @Bean
    public WireMockServer wireMockServer() {
        wireMockServer.start();
        return wireMockServer;
    }

    @PreDestroy
    public void stopWireMockServer(){
        wireMockServer.stop();
    }
}