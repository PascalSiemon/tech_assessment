package ch.psiemo.backend.api.server.dto;

import ch.psiemo.backend.api.mapper.NatWestMapper;
import ch.psiemo.backend.api.natwest.NatWestController;
import ch.psiemo.backend.api.natwest.dto.account.NatWestAccountResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ServerApi {

    @GetMapping("/accounts")
    public Customer getAccounts() {
        Customer customer = new Customer();

        NatWestAccountResponse natWestAccounts = NatWestController.getAccounts();

        Map<String, String> accountMappings = new HashMap<>();
        natWestAccounts.getAccountWrapper().getAccounts().forEach(
                (account) -> accountMappings.put(account.getAccountId(), account.getAccountType())
        );

        List<AccountBalance> accounts = new ArrayList<>();
        for (Map.Entry<String, String> accountMapping : accountMappings.entrySet()) {
            accounts.add(
                    NatWestMapper.toAccountBalance(
                            accountMapping.getValue(),
                            NatWestController.getBalance(accountMapping.getKey())
                    )
            );
        }

        customer.setAccounts(accounts);

        return customer;
    }
}
