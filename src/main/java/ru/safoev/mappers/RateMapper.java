package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.RateDto;
import ru.safoev.entity.RateEntity;

/**
 * Маппер для преобразования между сущностью RateEntity и DTO RateDto.
 * <p>
 * Этот компонент отвечает за преобразование данных между слоем сущностей
 * и слоем передачи данных для тарифов фитнес-центра.
 * Используется для изоляции логики преобразования данных тарифов.
 * </p>
 *
 * @Component указывает, что этот класс является Spring компонентом
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Component
public class RateMapper {

  /**
   * Преобразует сущность RateEntity в DTO RateDto.
   * <p>
   * Используется при получении данных тарифа из базы данных
   * для передачи клиенту API.
   * </p>
   *
   * @param entity сущность тарифа из базы данных
   * @return DTO тарифа для передачи данных
   */
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

  /**
   * Преобразует DTO RateDto в сущность RateEntity.
   * <p>
   * Используется при создании нового тарифа или полном обновлении
   * существующего тарифа.
   * </p>
   *
   * @param dto DTO тарифа, полученный от клиента API
   * @return сущность тарифа для сохранения в базе данных
   */
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

  /**
   * Обновляет существующую сущность RateEntity данными из DTO RateDto.
   * <p>
   * Используется для частичного обновления тарифа.
   * Обновляются только те поля, которые не являются null в DTO.
   * </p>
   *
   * @param dto DTO с новыми данными тарифа
   * @param entity существующая сущность тарифа, которую нужно обновить
   */
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