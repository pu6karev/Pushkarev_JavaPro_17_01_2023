package ua.ithillel.bank.versioning;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.ithillel.bank.versioning.currency.CurrencyConverter;
import ua.ithillel.bank.versioning.currency.model.ResponseApi;
import ua.ithillel.bank.versioning.currency.model.ResponseData;
import ua.ithillel.bank.versioning.reposytory.Account;
import ua.ithillel.bank.versioning.reposytory.AccountRepository;
import ua.ithillel.bank.versioning.service.AccountDto;
import ua.ithillel.bank.versioning.service.PersonOperationsService;

import java.io.IOException;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AccountControllerIntegrationTest {
    @Autowired
    protected AccountRepository accountRepository;
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected WireMockServer wireMockServer;
    @Autowired
    protected CurrencyConverter currencyConverter;

    @Autowired
    protected PersonOperationsService personOperationsService;

    @Test
    public void shouldPersonOperationsServiceConvert() throws Exception {
        setCurrencyWiremock();

        // --- получим правильный формат валюты
        Currency usd = Currency.getInstance("USD");
        Currency eur = Currency.getInstance("EUR");

        CompletableFuture<Double> conversionResult1 = personOperationsService.convert(usd, eur, 1000);
        double usdToEur = conversionResult1.get();
        CompletableFuture<Double> conversionResult2 = personOperationsService.convert(eur, usd, 1000);
        double eurToUsd = conversionResult2.get();

        double delta = 0.0001;
        assertEquals(usdToEur, (0.97/1)*1000, delta);
        assertEquals(eurToUsd, (1/0.97)*1000, delta);
    }

    // --- передаем данные на вебсервис в формате нашего класса, используя objectMapper и проверяем ф-ю конвертера валют
    @Test
    public void shouldCreateApiCurrencyWireMock() throws IOException {
        setCurrencyWiremock();

        // --- получим правильный формат валюты
        Currency usd = Currency.getInstance("USD");
        Currency eur = Currency.getInstance("EUR");

        // --- ковертируем 100 долларов в евро и 100 евро в доллары.
        double usdToEur = currencyConverter.convert(usd, eur, 100);
        double eurToUsd = currencyConverter.convert(eur, usd, 100);
        double delta = 0.0001;

        assertEquals(usdToEur, (0.97/1)*100, delta);
        assertEquals(eurToUsd, (1/0.97)*100, delta);
    }

    private void setCurrencyWiremock() throws IOException{
        // --- задаем две валюты USD и EUR с их курсом и укладываем в правильный формат класса ResponseApi
        ResponseData currencyUSD = ResponseData.builder()
                .code("USD")
                .value(1)
                .build();
        ResponseData currencyEUR = ResponseData.builder()
                .code("EUR")
                .value(0.97)
                .build();

        Map<String, ResponseData> dataMap = new HashMap<>();
        dataMap.put("USD", currencyUSD);
        dataMap.put("EUR", currencyEUR);

        ResponseApi response = ResponseApi.builder()
                .data(dataMap)
                .build();

        // ---
        wireMockServer.stubFor(WireMock.get(urlEqualTo("/v3/latest?apikey=testapikey"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(response))));
    }


    @Test
    public void shouldCreateAccount() throws Exception {
        accountRepository.save(Account.builder()
                .uid(UUID.randomUUID().toString())
                .iban("UA260000005555")
                .balance(23400)
                .personId(1L)
                .build());

        accountRepository.save(Account.builder()
                .uid(UUID.randomUUID().toString())
                .iban("UA260000004444")
                .balance(500)
                .personId(2L)
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldGetAllAccounts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldGetAccountByUid() throws Exception {
        // --- так как в методе shouldCreateAccount() уже создавали, извлечем счет
        Account account = accountRepository.findByIban("UA260000004444").orElse(null);

        mockMvc.perform(get("/api/accounts/{uid}", account.getUid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.iban").value(account.getIban()))
                .andExpect(jsonPath("$.balance").value(account.getBalance()))
                .andExpect(jsonPath("$.personId").value(account.getPersonId()));
    }

    @Test
    public void shouldUpdateAccount() throws Exception {
        // --- так как в методе shouldCreateAccount() уже создавали, извлечем счет
        Account account = accountRepository.findByIban("UA260000004444").orElse(null);
        // --- обновим в аккаунте баланс вместе с созданием DTO
        AccountDto updatedAccountDto = AccountDto.builder()
                .uid(account.getUid())
                .iban(account.getIban())
                .balance(1200)
                .personId(account.getPersonId())
                .build();

        mockMvc.perform(put("/api/accounts/{uid}", account.getUid())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedAccountDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(updatedAccountDto.balance()));
    }

    // --- преобразует объект в его JSON-представлени
    private static String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldDeleteAccount() throws Exception {
        // --- так как в методе shouldCreateAccount() уже создавали, извлечем счет
        Account account = accountRepository.findByIban("UA260000004444").orElseThrow();

        // Выполнение запроса на удаление аккаунта
        mockMvc.perform(delete("/api/accounts/{uid}", account.getUid()))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts"))
                .andExpect(jsonPath("$", hasSize(1)));
    }
}