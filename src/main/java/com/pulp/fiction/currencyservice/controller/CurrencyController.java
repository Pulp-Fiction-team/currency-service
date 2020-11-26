package com.pulp.fiction.currencyservice.controller;

import com.pulp.fiction.currencyservice.model.dto.CurrencyDto;
import com.pulp.fiction.currencyservice.model.entity.Currency;
import com.pulp.fiction.currencyservice.model.mapper.CurrencyMapper;
import com.pulp.fiction.currencyservice.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyMapper currencyMapper;

    @RequestMapping(path = "/currency", method = RequestMethod.POST)
    public ResponseEntity<Flux<Currency>> saveAuthor(@RequestBody CurrencyDto currencyDto) {
        Currency currency = currencyMapper.currencyDtoToCurrency(currencyDto);
        return ResponseEntity.ok(
                currencyService.save(
                        currency
                )
        );
    }

    @RequestMapping(value = "/currencies", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public ResponseEntity<Flux<CurrencyDto>> getAllAuthors() {
        return ResponseEntity.ok(currencyService.findAll().map(currency -> currencyMapper.currencyToCurrencyDto(currency)));
    }

    @RequestMapping(value = "/currencies", method = RequestMethod.DELETE)
    public ResponseEntity<Mono<Void>> deleteAll(){
        return ResponseEntity.ok(currencyService.deleteAll());
    }
}
