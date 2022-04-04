package com.bank.card.bankcard.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "card")
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_limit")
    private BigDecimal totalLimit;

    @Column(name = "amount_used")
    private BigDecimal amountUsed;

    @Column(name = "available_amount")
    private BigDecimal availableAmount;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
