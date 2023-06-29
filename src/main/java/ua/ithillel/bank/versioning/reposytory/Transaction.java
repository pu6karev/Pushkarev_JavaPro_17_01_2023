package ua.ithillel.bank.versioning.reposytory;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "transactions")


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends  BaseEntity{
    private String uid;
    private String ibanFrom;
    private String ibanTo;
    private int value;
    private String currency;
}
