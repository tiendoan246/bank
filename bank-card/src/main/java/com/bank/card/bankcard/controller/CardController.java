package com.bank.card.bankcard.controller;

import com.bank.card.bankcard.model.CardModel;
import com.bank.card.bankcard.model.request.GetCardDetailsRequest;
import com.bank.card.bankcard.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    private final Logger logger = LoggerFactory.getLogger(CardController.class);
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/cards")
    public ResponseEntity<CardModel> getCardDetails(@RequestBody GetCardDetailsRequest request) {
        logger.info("Start reading cards from customer ID {}", request.getCustomerId());
        return cardService.findByCustomerId(request.getCustomerId());
    }
}
