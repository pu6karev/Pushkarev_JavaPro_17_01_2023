package ua.ithillel.bank.versioning.reposytory;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "accounts")

@Builder
@NoArgsConstructor

public class Account extends BaseEntity {
    private String uid;
    private String iban;
    private int balance;

    private long personId;

    private String currency;

    public Account(String uid, String iban, int balance, long personId, String currency) {
        this.uid = uid;
        this.iban = iban;
        this.balance = balance;
        this.personId = personId;
        setCurrency(currency);
    }

    // --- проверка на обязательность значения поля currency
    public void setCurrency(String currency) {
        if (currency == null || currency.isEmpty()) {
            throw new IllegalArgumentException("Currency must be provided");
        }
        this.currency = currency;
    }
}
