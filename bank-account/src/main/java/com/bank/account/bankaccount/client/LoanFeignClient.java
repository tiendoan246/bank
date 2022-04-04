package com.bank.account.bankaccount.client;

import com.bank.account.bankaccount.model.request.GetLoansRequest;
import com.bank.account.bankaccount.model.response.LoanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("loan")
public interface LoanFeignClient {

    @PostMapping(value = "loans", consumes = "application/json")
    ResponseEntity<List<LoanResponse>> getLoans(@RequestBody GetLoansRequest request);
}
