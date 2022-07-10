package ch.psiemo.backend.api.natwest.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountDetails {

    @JsonProperty("AccountId")
    private String accountId;

    @JsonProperty("Currency")
    private String currency;

    @JsonProperty("AccountType")
    private String accountType;

    @JsonProperty("AccountSubType")
    private String accountSubType;

    @JsonProperty("Description")
    private String description;

}
