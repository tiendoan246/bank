package com.bank.account.bankaccount.model;

import com.bank.account.bankaccount.model.response.CardResponse;
import com.bank.account.bankaccount.model.response.LoanResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerDetails {
    private Account account;
    private List<LoanResponse> loans;
    private CardResponse card;
}
