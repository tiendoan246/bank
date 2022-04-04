package com.bank.card.bankcard.repository;

import com.bank.card.bankcard.repository.entity.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    Card findByCustomerId(String customerId);
}
