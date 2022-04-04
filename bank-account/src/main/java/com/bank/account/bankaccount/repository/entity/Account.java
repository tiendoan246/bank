package com.bank.account.bankaccount.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "status")
    private String status;
}
