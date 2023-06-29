package ua.ithillel.bank.versioning.service;

import lombok.Builder;
import ua.ithillel.bank.versioning.reposytory.Amount;

import java.time.Instant;

@Builder
public record TransactionDto(String uid, String ibanFrom, String ibanTo, Amount amount, Instant createdAt) {
}
