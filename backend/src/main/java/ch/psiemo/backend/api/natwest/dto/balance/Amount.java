package ch.psiemo.backend.api.natwest.dto.balance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Amount {

    @JsonProperty("Amount")
    private Double amount;

    @JsonProperty("Currency")
    private String currency;

}
