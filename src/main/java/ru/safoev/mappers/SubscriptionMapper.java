package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.SubscriptionDto;
import ru.safoev.entity.SubscriptionEntity;
import ru.safoev.entity.ClientEntity;
import ru.safoev.entity.RateEntity;
import ru.safoev.enumlists.SubscriptionStatus;

/**
 * Маппер для преобразования между сущностью SubscriptionEntity и DTO SubscriptionDto.
 * <p>
 * Этот компонент отвечает за преобразование данных между слоем сущностей
 * и слоем передачи данных для абонементов фитнес-центра.
 * Обрабатывает связи с сущностями ClientEntity и RateEntity,
 * а также преобразование статуса абонемента между строковым представлением и enum.
 * </p>
 *
 * @Component указывает, что этот класс является Spring компонентом
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 */
@Component
public class SubscriptionMapper {

  /**
   * Преобразует сущность SubscriptionEntity в DTO SubscriptionDto.
   * <p>
   * Используется при получении данных абонемента из базы данных
   * для передачи клиенту API. Извлекает идентификаторы клиента и тарифа
   * из связанных сущностей и преобразует статус из enum в строку.
   * </p>
   *
   * @param entity сущность абонемента из базы данных
   * @return DTO абонемента для передачи данных
   */
  public SubscriptionDto toDto(SubscriptionEntity entity) {
    Long clientId = entity.getClient() != null ? entity.getClient().getClient_id() : null;
    Long rateId = entity.getRate() != null ? entity.getRate().getRate_id() : null;
    String status = entity.getSubscription_status() != null ?
            entity.getSubscription_status().name() : null;

    return new SubscriptionDto(
            entity.getSubscription_id(),
            clientId,
            rateId,
            entity.getSubscription_startDate(),
            entity.getSubscription_endDate(),
            entity.getSubscription_freezePeriod(),
            status
    );
  }

  /**
   * Преобразует DTO SubscriptionDto в сущность SubscriptionEntity.
   * <p>
   * Используется при создании нового абонемента или полном обновлении
   * существующего абонемента. Требует передачу связанных сущностей
   * ClientEntity и RateEntity.
   * </p>
   *
   * @param dto DTO абонемента, полученный от клиента API
   * @param client сущность клиента, связанная с абонементом
   * @param rate сущность тарифа, связанная с абонементом
   * @return сущность абонемента для сохранения в базе данных
   */
  public SubscriptionEntity toEntity(SubscriptionDto dto, ClientEntity client, RateEntity rate) {
    SubscriptionEntity entity = new SubscriptionEntity();
    entity.setSubscription_id(dto.subscriptionId());
    entity.setClient(client);
    entity.setRate(rate);
    entity.setSubscription_startDate(dto.startDate());
    entity.setSubscription_endDate(dto.endDate());
    entity.setSubscription_freezePeriod(dto.freezePeriod());

    SubscriptionStatus status = dto.subscriptionStatus() != null ?
            SubscriptionStatus.valueOf(dto.subscriptionStatus()) : SubscriptionStatus.ACTIVE;
    entity.setSubscription_status(status);

    return entity;
  }

  /**
   * Обновляет существующую сущность SubscriptionEntity данными из DTO SubscriptionDto.
   * <p>
   * Используется для частичного обновления абонемента.
   * Обновляются только те поля, которые не являются null в DTO.
   * Принимает необязательные параметры client и rate для обновления связанных сущностей.
   * </p>
   *
   * @param dto DTO с новыми данными абонемента
   * @param entity существующая сущность абонемента, которую нужно обновить
   * @param client сущность клиента для обновления связи (может быть null)
   * @param rate сущность тарифа для обновления связи (может быть null)
   */
  public void updateEntityFromDto(SubscriptionDto dto, SubscriptionEntity entity,
                                  ClientEntity client, RateEntity rate) {
    if (client != null) {
      entity.setClient(client);
    }
    if (rate != null) {
      entity.setRate(rate);
    }
    if (dto.startDate() != null) {
      entity.setSubscription_startDate(dto.startDate());
    }
    if (dto.endDate() != null) {
      entity.setSubscription_endDate(dto.endDate());
    }
    if (dto.freezePeriod() != null) {
      entity.setSubscription_freezePeriod(dto.freezePeriod());
    }
    if (dto.subscriptionStatus() != null) {
      SubscriptionStatus status = SubscriptionStatus.valueOf(dto.subscriptionStatus());
      entity.setSubscription_status(status);
    }
  }
}