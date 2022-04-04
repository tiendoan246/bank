package com.bank.account.bankaccount.repository;

import com.bank.account.bankaccount.repository.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    List<Account> findByCustomerId(String customerId);
    Account findFirstByCustomerId(String customerId);
}
