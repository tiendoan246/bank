package com.bank.account.bankaccount.service;

import com.bank.account.bankaccount.model.Account;
import com.bank.account.bankaccount.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<Account> findAccount(String accountId) {
        com.bank.account.bankaccount.repository.entity.Account account = accountRepository.findById(accountId).orElse(null);

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(Account.builder()
                .accountNumber(account.getAccountNumber())
                .customerId(account.getCustomerId())
                .branchId(account.getBranchId())
                .balance(account.getBalance())
                .status(account.getStatus())
                .accountType(account.getAccountType())
                .createdDate(account.getCreatedDate())
                .build(), HttpStatus.OK);
    }

    public ResponseEntity<List<Account>> findByCustomerId(String customerId) {
        List<com.bank.account.bankaccount.repository.entity.Account> account = accountRepository.findByCustomerId(customerId);

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(account.stream().map(a -> Account.builder()
                .accountNumber(a.getAccountNumber())
                .customerId(a.getCustomerId())
                .branchId(a.getBranchId())
                .balance(a.getBalance())
                .status(a.getStatus())
                .accountType(a.getAccountType())
                .createdDate(a.getCreatedDate())
                .build()).collect(Collectors.toList()), HttpStatus.OK);
    }

    public Account findFirstByCustomerId(String customerId) {
        com.bank.account.bankaccount.repository.entity.Account account = accountRepository.findFirstByCustomerId(customerId);

        if (account == null) {
            return null;
        }

        return Account.builder()
                .accountNumber(account.getAccountNumber())
                .customerId(account.getCustomerId())
                .branchId(account.getBranchId())
                .balance(account.getBalance())
                .status(account.getStatus())
                .accountType(account.getAccountType())
                .createdDate(account.getCreatedDate())
                .build();
    }
}
