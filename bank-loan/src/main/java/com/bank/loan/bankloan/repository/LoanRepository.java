package com.bank.loan.bankloan.repository;

import com.bank.loan.bankloan.repository.entity.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findByCustomerIdOrderByCreatedDateDesc(String customerId);
}
