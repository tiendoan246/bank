package com.bank.account.bankaccount.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class Account {
    private String accountNumber;
    private String customerId;
    private String branchId;
    private BigDecimal balance;
    private LocalDate createdDate;
    private String accountType;
    private String status;
}
