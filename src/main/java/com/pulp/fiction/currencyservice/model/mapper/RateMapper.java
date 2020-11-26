package com.pulp.fiction.currencyservice.model.mapper;

import com.pulp.fiction.currencyservice.model.dto.RateDto;
import com.pulp.fiction.currencyservice.model.entity.Rate;
import org.mapstruct.Mapper;


@Mapper
public interface RateMapper {
    RateDto rateToRateDto(Rate rate);
    Rate rateDtoToRate(RateDto rateDto);
}
