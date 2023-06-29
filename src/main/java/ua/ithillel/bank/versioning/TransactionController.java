package ua.ithillel.bank.versioning;

import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{transactionId}")
    public TransactionDto getTransaction(@PathVariable String transactionId) {
        return transactionService.getTransaction(transactionId);
    }
}
