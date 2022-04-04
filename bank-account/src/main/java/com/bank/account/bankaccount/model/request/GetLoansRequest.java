package com.bank.account.bankaccount.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetLoansRequest {
    private String customerId;
}
