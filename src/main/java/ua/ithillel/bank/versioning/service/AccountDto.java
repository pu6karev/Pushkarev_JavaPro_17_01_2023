package ua.ithillel.bank.versioning.service;

import lombok.Builder;

@Builder
public record AccountDto(String uid, String iban, int balance, long personId, String currency) {

}
