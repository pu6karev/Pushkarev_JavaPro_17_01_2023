package ua.ithillel.bank.versioning;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ithillel.bank.versioning.service.TransactionDto;
import ua.ithillel.bank.versioning.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public TransactionDto transferMoney(@RequestBody TransactionDto transaction) {
        return transactionService.transferBalance(transaction);
    }
}
