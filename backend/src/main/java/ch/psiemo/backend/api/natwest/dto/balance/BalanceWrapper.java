package ch.psiemo.backend.api.natwest.dto.balance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BalanceWrapper {

    @JsonProperty("Balance")
    private List<BalanceDetails> balances;

}
