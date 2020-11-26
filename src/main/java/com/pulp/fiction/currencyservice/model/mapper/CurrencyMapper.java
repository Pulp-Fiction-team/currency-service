package com.pulp.fiction.currencyservice.model.mapper;

import com.pulp.fiction.currencyservice.model.dto.CurrencyDto;
import com.pulp.fiction.currencyservice.model.entity.Currency;
import org.mapstruct.Mapper;

@Mapper
public interface CurrencyMapper {
    Currency currencyDtoToCurrency(CurrencyDto currencyDto);

    CurrencyDto currencyToCurrencyDto(Currency currency);
}
