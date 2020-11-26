package com.pulp.fiction.currencyservice.service;

import com.pulp.fiction.currencyservice.model.entity.Currency;
import com.pulp.fiction.currencyservice.model.entity.Rate;
import com.pulp.fiction.currencyservice.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Flux<Currency> save(Currency currency) {
        List<Rate> rates = currency.getRates();
        Map<String, Rate> currencyNameToRateMap = rates.stream()
                .collect(Collectors.toMap(Rate::getName, Function.identity()));

        Set<String> currencyNames = currencyNameToRateMap.keySet();
        List<Currency> currencies = currencyNames.stream()
                .map(
                        name -> {
                            Rate rate = currencyNameToRateMap.get(name);
                            String valueAsString = rate.getValue();
                            BigDecimal value = new BigDecimal(valueAsString);

                            Currency.CurrencyBuilder currencyBuilder = Currency.builder();
                            currencyBuilder.name(name);

                            List<Rate> newRates = currencyNames.stream()
                                    .map(
                                            otherName -> {
                                                Rate otherRate = currencyNameToRateMap.get(otherName);
                                                String otherValuesAsString = otherRate.getValue();
                                                BigDecimal otherValue = new BigDecimal(otherValuesAsString);

                                                BigDecimal newRate = otherValue.divide(value, 4, RoundingMode.HALF_EVEN);

                                                return Rate.builder()
                                                        .name(otherName)
                                                        .value(newRate.toString())
                                                        .build();
                                            }
                                    )
                                    .collect(Collectors.toList());
                            currencyBuilder.rates(newRates);
                            return currencyBuilder.build();
                        }
                )
                .collect(Collectors.toList());

        return currencyRepository.saveAll(currencies);
    }

    @Override
    public Flux<Currency> findAll(){
        return currencyRepository.findAll();
    }

    @Override
    public Mono<Void> deleteAll(){
        return currencyRepository.deleteAll();
    }
}
