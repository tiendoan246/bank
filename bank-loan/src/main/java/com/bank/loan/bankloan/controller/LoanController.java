package com.bank.loan.bankloan.controller;

import com.bank.loan.bankloan.model.LoanModel;
import com.bank.loan.bankloan.model.request.GetLoansRequest;
import com.bank.loan.bankloan.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoanController {

    private final Logger logger = LoggerFactory.getLogger(LoanController.class);
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/loans")
    public ResponseEntity<List<LoanModel>> getLoans(@RequestBody GetLoansRequest request) {
        logger.info("Start reading loans from customer ID {}", request.getCustomerId());
        return loanService.findByCustomerId(request.getCustomerId());
    }
}
