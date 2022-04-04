package com.bank.card.bankcard.service;

import com.bank.card.bankcard.model.CardModel;
import com.bank.card.bankcard.repository.CardRepository;
import com.bank.card.bankcard.repository.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public ResponseEntity<CardModel> findByCustomerId(String customerId) {
        Card card = cardRepository.findByCustomerId(customerId);

        if (card == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(CardModel.builder()
                .customerId(card.getCustomerId())
                .cardId(card.getCardId())
                .amountUsed(card.getAmountUsed())
                .availableAmount(card.getAvailableAmount())
                .cardNumber(card.getCardNumber())
                .cardType(card.getCardType())
                .createdDate(card.getCreatedDate())
                .build(), HttpStatus.OK);
    }
}
