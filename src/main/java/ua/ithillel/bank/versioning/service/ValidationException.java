package ua.ithillel.bank.versioning.service;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
