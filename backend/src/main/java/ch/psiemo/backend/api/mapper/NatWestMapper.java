package ch.psiemo.backend.api.mapper;

import ch.psiemo.backend.api.natwest.dto.balance.Amount;
import ch.psiemo.backend.api.natwest.dto.balance.NatWestBalanceResponse;
import ch.psiemo.backend.api.server.dto.AccountBalance;

public class NatWestMapper {

    public static AccountBalance toAccountBalance(String accountType, NatWestBalanceResponse response) {
        Amount amount = response.getBalanceWrapper().getBalances().stream()
                .filter((balance) -> balance.getType().equals("InterimAvailable"))
                .toList()
                .get(0)
                .getAmount();

        return AccountBalance.builder()
                .accountType(accountType)
                .currency(amount.getCurrency())
                .amount(amount.getAmount())
                .build();
    }

}
