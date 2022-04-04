package com.bank.account.bankaccount.model.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class LoanResponse {
    private Long loanNumber;
    private String customerId;
    private LocalDate startDate;
    private String loanType;
    private BigDecimal totalLoan;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmount;
    private LocalDate createdDate;
}
