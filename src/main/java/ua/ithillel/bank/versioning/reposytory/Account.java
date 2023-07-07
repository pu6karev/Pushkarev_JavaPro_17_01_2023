package ua.ithillel.bank.versioning.reposytory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "accounts")

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {
    private String uid;
    @Column(unique = true)
    private String iban;
    private int balance;

    private long personId;

    @Column(nullable = false)
    private String currency;

    public void decreaseBalance(int amount) {
        int newBalance = balance - amount;
        setBalance(newBalance);
    }

    public void increaseBalance(int amount) {
        int newBalance = balance + amount;
        setBalance(newBalance);
    }
}
