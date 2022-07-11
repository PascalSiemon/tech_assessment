package ch.psiemo.backend.api.server;

import ch.psiemo.backend.api.mapper.NatWestMapper;
import ch.psiemo.backend.api.natwest.NatWestApi;
import ch.psiemo.backend.api.natwest.dto.account.NatWestAccountResponse;
import ch.psiemo.backend.api.server.dto.AccountBalance;
import ch.psiemo.backend.api.server.dto.Customer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

    @GetMapping("/accounts")
    @CrossOrigin(origins = "http://localhost:4200")
    public Customer getAccounts() {
        Customer customer = new Customer();

        NatWestAccountResponse natWestAccounts = NatWestApi.getAccounts();

        Map<String, String> accountMappings = new HashMap<>();
        natWestAccounts.getAccountWrapper().getAccounts().forEach(
                (account) -> accountMappings.put(account.getAccountId(), account.getAccountSubType())
        );

        List<AccountBalance> accounts = new ArrayList<>();
        for (Map.Entry<String, String> accountMapping : accountMappings.entrySet()) {
            accounts.add(
                    NatWestMapper.toAccountBalance(
                            accountMapping.getValue(),
                            NatWestApi.getBalance(accountMapping.getKey())
                    )
            );
        }

        customer.setAccounts(accounts);

        return customer;
    }
}
