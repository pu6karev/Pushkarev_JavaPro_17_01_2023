package ua.ithillel.bank.versioning.service;

public class AccountNotFoundException extends  RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
