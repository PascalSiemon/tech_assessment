package ch.psiemo.backend.api.natwest.dto.balance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BalanceDetails {

    @JsonProperty("AccountId")
    private String accountId;

    @JsonProperty("Amount")
    private Amount amount;

    @JsonProperty("CreditDebitIndicator")
    private String creditDebitIndicator;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("DateTime")
    private Date dateTime;

}
