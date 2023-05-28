package ua.ithillel.bank.versioning.reposytory;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "persons")

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntity{
    private String uid;
    private String name;
    private String email;

}
