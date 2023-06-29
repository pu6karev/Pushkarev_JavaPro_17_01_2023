package ua.ithillel.bank.versioning;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.ithillel.bank.versioning.currency.CurrencyConverter;
import ua.ithillel.bank.versioning.currency.model.ResponseApi;
import ua.ithillel.bank.versioning.currency.model.ResponseData;
import ua.ithillel.bank.versioning.reposytory.Account;
import ua.ithillel.bank.versioning.reposytory.AccountRepository;
import ua.ithillel.bank.versioning.reposytory.Amount;
import ua.ithillel.bank.versioning.service.TransactionDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TransactionControllerIntegrationTest {
    @Autowired
    protected AccountRepository accountRepository;
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected WireMockServer wireMockServer;
    @Autowired
    protected CurrencyConverter currencyConverter;

    @Autowired
    protected ObjectMapper objectMapper;

    private Account createAccount(String iban, int balance, long personId, String currency) {
        Account account = Account.builder()
                .uid(UUID.randomUUID().toString())
                .iban(iban)
                .balance(balance)
                .personId(personId)
                .currency(currency)
                .build();

        return accountRepository.save(account);
    }
    @Test
    void shouldTransferMoney() throws Exception {

        var fromAccount = createAccount("UA26000000555511", 23400, 1L, "UAH");
        var toAccount = createAccount("UA26000000444422", 500, 2L, "UAH");

        // --- создаем запрос на перевод
        TransactionDto transactionDto = TransactionDto.builder()
                .ibanFrom(fromAccount.getIban())
                .ibanTo(toAccount.getIban())
                .amount(new Amount("100", "UAH"))
                .build();

        // --- отправляем POST-запрос на перевод
        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ibanFrom").value(fromAccount.getIban()))
                .andExpect(jsonPath("$.ibanTo").value(toAccount.getIban()))
                .andExpect(jsonPath("$.amount.value").value("100"))
                .andExpect(jsonPath("$.amount.currency").value("UAH"));

        // --- проверяем, что балансы изменились
        Account updatedFromAccount = accountRepository.findByIban(fromAccount.getIban()).orElse(null);
        Account updatedToAccount = accountRepository.findByIban(toAccount.getIban()).orElse(null);

        assertNotNull(updatedFromAccount);
        assertNotNull(updatedToAccount);

        assertEquals(fromAccount.getBalance() - 100, updatedFromAccount.getBalance());
        assertEquals(toAccount.getBalance() + 100, updatedToAccount.getBalance());
    }

    @Test
    void shouldGetTransaction() throws Exception {

        var fromAccount = createAccount("UA2600000055551", 23400, 3L, "UAH");
        var toAccount = createAccount("UA2600000044442", 500, 4L, "UAH");

        // --- создаем запрос на перевод
        TransactionDto transactionDto = TransactionDto.builder()
                .ibanFrom(fromAccount.getIban())
                .ibanTo(toAccount.getIban())
                .amount(new Amount("100", "UAH"))
                .build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDto)))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        TransactionDto createdTransaction = objectMapper.readValue(responseJson, TransactionDto.class);
        String transactionId = createdTransaction.uid();

        // --- получение созданной транзакции
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions/{transactionId}", transactionId))
                .andExpect(status().isOk())
                .andReturn();

        String getResponseJson = mvcResult.getResponse().getContentAsString();
        TransactionDto retrievedTransaction = objectMapper.readValue(getResponseJson, TransactionDto.class);

        // --- проверка значений в полученной транзакции
        assertEquals("UA2600000055551", retrievedTransaction.ibanFrom());
        assertEquals("UA2600000044442", retrievedTransaction.ibanTo());
        assertEquals("100", retrievedTransaction.amount().getValue());
        assertEquals("UAH", retrievedTransaction.amount().getCurrency());
    }

    @Test
    void shouldTransferDifferentCurrency() throws Exception {
        // --- задаем две валюты USD и UAH с их курсом и укладываем в правильный формат класса ResponseApi
        setCurrencyWiremock();

        var fromAccount = createAccount("UA2600000055557", 1000, 5L, "UAH");
        var toAccount = createAccount("UA2600000044448", 50, 6L, "USD");

        // --- создаем запрос на перевод
        TransactionDto transactionDto = TransactionDto.builder()
                .ibanFrom(fromAccount.getIban())
                .ibanTo(toAccount.getIban())
                .amount(new Amount("100", "UAH"))
                .build();

        // --- отправляем POST-запрос на перевод
        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ibanFrom").value(fromAccount.getIban()))
                .andExpect(jsonPath("$.ibanTo").value(toAccount.getIban()))
                .andExpect(jsonPath("$.amount.value").value("100"))
                .andExpect(jsonPath("$.amount.currency").value("UAH"));

        // --- проверяем, что балансы изменились
        Account updatedFromAccount = accountRepository.findByIban(fromAccount.getIban()).orElse(null);
        Account updatedToAccount = accountRepository.findByIban(toAccount.getIban()).orElse(null);

        assertNotNull(updatedFromAccount);
        assertNotNull(updatedToAccount);

        assertEquals(fromAccount.getBalance() - 100, updatedFromAccount.getBalance());
        assertEquals(toAccount.getBalance() + (int)(100/36.7), updatedToAccount.getBalance());
    }

    private void setCurrencyWiremock() throws IOException {
        // --- задаем две валюты USD и UAH с их курсом и укладываем в правильный формат класса ResponseApi
        ResponseData currencyUSD = ResponseData.builder()
                .code("USD")
                .value(1)
                .build();
        ResponseData currencyUAH = ResponseData.builder()
                .code("UAH")
                .value(36.7)
                .build();

        Map<String, ResponseData> dataMap = new HashMap<>();
        dataMap.put("USD", currencyUSD);
        dataMap.put("UAH", currencyUAH);

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
}