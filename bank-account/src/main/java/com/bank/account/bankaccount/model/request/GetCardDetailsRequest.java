package com.bank.account.bankaccount.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCardDetailsRequest {
    private String customerId;
}
