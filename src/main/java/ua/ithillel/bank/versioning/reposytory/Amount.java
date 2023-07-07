package ua.ithillel.bank.versioning.reposytory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Amount {
    private String value;
    private String currency;
}
