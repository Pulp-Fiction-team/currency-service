package com.pulp.fiction.currencyservice.repository;

import com.pulp.fiction.currencyservice.model.entity.Currency;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CurrencyRepository extends ReactiveMongoRepository<Currency, String> {

    @Override
    Flux<Currency> findAll();

    Flux<Currency> findByName(String s);

    @Override
    <S extends Currency> Mono<S> save(S s);

    @Override
    Mono<Void> deleteAll();

    @Override
    <S extends Currency> Flux<S> saveAll(Iterable<S> iterable);


}
