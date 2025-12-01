package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.RateDto;
import ru.safoev.entity.RateEntity;

@Component
public class RateMapper {

  public RateDto toDto(RateEntity entity) {
    return new RateDto(
            entity.getRate_id(),
            entity.getRate_name(),
            entity.getRate_price(),
            entity.getRate_price_period(),
            entity.getRate_duration_days(),
            entity.getRate_description()
    );
  }

  public RateEntity toEntity(RateDto dto) {
    return new RateEntity(
            dto.rateId(),
            dto.rateName(),
            dto.price(),
            dto.pricePeriod(),
            dto.durationDays(),
            dto.description()
    );
  }

  public void updateEntityFromDto(RateDto dto, RateEntity entity) {
    if (dto.rateName() != null) {
      entity.setRate_name(dto.rateName());
    }
    if (dto.price() != null) {
      entity.setRate_price(dto.price());
    }
    if (dto.pricePeriod() != null) {
      entity.setRate_price_period(dto.pricePeriod());
    }
    if (dto.durationDays() != null) {
      entity.setRate_duration_days(dto.durationDays());
    }
    if (dto.description() != null) {
      entity.setRate_description(dto.description());
    }
  }
}