package ua.ithillel.bank.versioning;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.ithillel.bank.versioning.reposytory.Account;
import ua.ithillel.bank.versioning.reposytory.AccountRepository;
import ua.ithillel.bank.versioning.service.AccountDto;

import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerIntegrationTest {
    @Autowired
    protected AccountRepository accountRepository;
    @Autowired
    protected MockMvc mockMvc;

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

        mockMvc.perform(get("/api/accounts"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldGetAllAccounts() throws Exception {
        mockMvc.perform(get("/api/accounts"))
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

        mockMvc.perform(get("/api/accounts"))
                .andExpect(jsonPath("$", hasSize(1)));
    }
}