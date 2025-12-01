package ru.safoev.mappers;

import org.springframework.stereotype.Component;
import ru.safoev.dtorecords.SubscriptionDto;
import ru.safoev.entity.SubscriptionEntity;
import ru.safoev.entity.ClientEntity;
import ru.safoev.entity.RateEntity;
import ru.safoev.enumlists.SubscriptionStatus;

@Component
public class SubscriptionMapper {

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