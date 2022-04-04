package com.bank.loan.bankloan.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class LoanModel {
    private Long loanNumber;
    private String customerId;
    private LocalDate startDate;
    private String loanType;
    private BigDecimal totalLoan;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmount;
    private LocalDate createdDate;
}
