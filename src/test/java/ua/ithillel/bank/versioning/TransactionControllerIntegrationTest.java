package ua.ithillel.bank.versioning;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.ithillel.bank.versioning.reposytory.Account;
import ua.ithillel.bank.versioning.reposytory.AccountRepository;
import ua.ithillel.bank.versioning.reposytory.Amount;
import ua.ithillel.bank.versioning.service.TransactionDto;

import java.util.UUID;

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
    protected ObjectMapper objectMapper;

    @Test
    void transferMoney() throws Exception {
        Account fromAccount = Account.builder()
                .uid(UUID.randomUUID().toString())
                .iban("UA2600000055551")
                .balance(23400)
                .personId(1L)
                .currency("UAH")
                .build();

        Account toAccount = Account.builder()
                .uid(UUID.randomUUID().toString())
                .iban("UA2600000044442")
                .balance(500)
                .personId(2L)
                .currency("UAH")
                .build();

        fromAccount = accountRepository.save(fromAccount);
        toAccount = accountRepository.save(toAccount);

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
}