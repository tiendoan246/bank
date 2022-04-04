package com.bank.loan.bankloan.service;

import com.bank.loan.bankloan.model.LoanModel;
import com.bank.loan.bankloan.repository.LoanRepository;
import com.bank.loan.bankloan.repository.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public ResponseEntity<List<LoanModel>> findByCustomerId(String customerId) {
        List<Loan> loans = loanRepository.findByCustomerIdOrderByCreatedDateDesc(customerId);

        if (loans == null || loans.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(loans.stream().map(a -> LoanModel.builder()
                .loanNumber(a.getLoanNumber())
                .customerId(a.getCustomerId())
                .amountPaid(a.getAmountPaid())
                .outstandingAmount(a.getOutstandingAmount())
                .totalLoan(a.getTotalLoan())
                .build()).collect(Collectors.toList()), HttpStatus.OK);
    }
}
