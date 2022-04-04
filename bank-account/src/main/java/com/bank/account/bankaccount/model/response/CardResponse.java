package com.bank.account.bankaccount.model.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class CardResponse {
    private Long cardId;
    private String customerId;
    private String cardNumber;
    private String cardType;
    private BigDecimal totalLimit;
    private BigDecimal amountUsed;
    private BigDecimal availableAmount;
    private LocalDateTime createdDate;
}
