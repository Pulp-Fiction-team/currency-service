package com.pulp.fiction.currencyservice.service;

import com.pulp.fiction.currencyservice.model.entity.Currency;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrencyService {
    Flux<Currency> save(Currency currency);

    Flux<Currency> findAll();

    Flux<Currency> findByName(String name);

    Mono<Void> deleteAll();
}
