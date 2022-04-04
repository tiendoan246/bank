package com.bank.account.bankaccount.controller;

import com.bank.account.bankaccount.client.CardFeignClient;
import com.bank.account.bankaccount.client.LoanFeignClient;
import com.bank.account.bankaccount.model.Account;
import com.bank.account.bankaccount.model.CustomerDetails;
import com.bank.account.bankaccount.model.request.GetAccountsRequest;
import com.bank.account.bankaccount.model.request.GetCardDetailsRequest;
import com.bank.account.bankaccount.model.request.GetLoansRequest;
import com.bank.account.bankaccount.model.response.CardResponse;
import com.bank.account.bankaccount.model.response.LoanResponse;
import com.bank.account.bankaccount.service.AccountService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;
    private final CardFeignClient cardFeignClient;
    private final LoanFeignClient loanFeignClient;

    @Autowired
    public AccountController(AccountService accountService, CardFeignClient cardFeignClient, LoanFeignClient loanFeignClient) {
        this.accountService = accountService;
        this.cardFeignClient = cardFeignClient;
        this.loanFeignClient = loanFeignClient;
    }

    @PostMapping("/accounts")
    @Timed(value = "getAccounts.time", description = "Time taken to return Account Details")
    public ResponseEntity<List<Account>> getAccounts(@RequestBody GetAccountsRequest request) {
        logger.info("Start reading accounts");
        return accountService.findByCustomerId(request.getCustomerId());
    }

    @GetMapping("/accounts/{accountId}")
    @RateLimiter(name = "accountRateLimit", fallbackMethod = "tooManyRequestFallback")
    public ResponseEntity<?> getAccount(@PathVariable("accountId") String accountId) {
        logger.info("Start reading accounts from account ID {}", accountId);
        return accountService.findAccount(accountId);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDetails> getCustomerDetails(@PathVariable("customerId") String customerId) {
        logger.info("Start reading accounts from customer ID {}", customerId);
        return getCustomer(customerId);
    }

    @GetMapping("/customers/circuitBreaker/{customerId}")
    @CircuitBreaker(name = "detailsForCustomerSupportApp", fallbackMethod = "customerFallback")
    public ResponseEntity<CustomerDetails> getCustomerDetailsWithBreaker(@PathVariable("customerId") String customerId) {
        logger.info("Start reading accounts from customer ID {}", customerId);
        return getCustomer(customerId);
    }

    private ResponseEntity<CustomerDetails> getCustomer(String customerId) {
        Account account = accountService.findFirstByCustomerId(customerId);

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CardResponse cardResponse = cardFeignClient
                .getCardDetails(GetCardDetailsRequest.builder().customerId(customerId).build()).getBody();
        List<LoanResponse> loanResponse = loanFeignClient
                .getLoans(GetLoansRequest.builder().customerId(customerId).build()).getBody();

        return new ResponseEntity<>(CustomerDetails.builder()
                .account(account)
                .card(cardResponse)
                .loans(loanResponse)
                .build(), HttpStatus.OK);
    }

    private ResponseEntity<CustomerDetails> customerFallback(String customerId, Throwable throwable) {
        Account account = accountService.findFirstByCustomerId(customerId);

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(CustomerDetails.builder()
                .account(account)
                .build(), HttpStatus.OK);
    }

    private ResponseEntity<?> tooManyRequestFallback(String accountId, Throwable throwable) {
        return new ResponseEntity<>("Too many requests", HttpStatus.OK);
    }
}
