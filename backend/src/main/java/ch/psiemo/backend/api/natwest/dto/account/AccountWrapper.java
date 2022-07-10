package ch.psiemo.backend.api.natwest.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AccountWrapper {

    @JsonProperty("Account")
    List<AccountDetails> accounts;

}
