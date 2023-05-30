package ua.ithillel.bank.versioning.reposytory;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "accounts")

@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Account extends BaseEntity {
    private String uid;
    private String iban;

    private int balance;
}
