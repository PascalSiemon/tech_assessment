package ch.psiemo.backend.api.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Customer {

    @JsonProperty("accounts")
    List<AccountBalance> accounts;

}
