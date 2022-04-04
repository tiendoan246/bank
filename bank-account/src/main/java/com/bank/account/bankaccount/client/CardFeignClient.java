package com.bank.account.bankaccount.client;

import com.bank.account.bankaccount.model.request.GetCardDetailsRequest;
import com.bank.account.bankaccount.model.response.CardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("card")
public interface CardFeignClient {

    @PostMapping(value = "cards", consumes = "application/json")
    ResponseEntity<CardResponse> getCardDetails(@RequestBody GetCardDetailsRequest request);

}
