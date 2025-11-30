package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safoev.dtorecords.SubscriptionDto;
import ru.safoev.entity.SubscriptionEntity;
import ru.safoev.entity.ClientEntity;
import ru.safoev.entity.RateEntity;
import ru.safoev.enumlists.SubscriptionStatus;
import ru.safoev.repositoryinterface.SubscriptionRepository;
import ru.safoev.repositoryinterface.ClientRepository;
import ru.safoev.repositoryinterface.RateRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;
  private final ClientRepository clientRepository;
  private final RateRepository rateRepository;

  @Autowired
  public SubscriptionService(SubscriptionRepository subscriptionRepository,
                             ClientRepository clientRepository,
                             RateRepository rateRepository) {
    this.subscriptionRepository = subscriptionRepository;
    this.clientRepository = clientRepository;
    this.rateRepository = rateRepository;
  }

  public SubscriptionDto getSubscriptionById(Long id) {
    SubscriptionEntity subscription = subscriptionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Subscription not found with id: " + id));
    return toDtoSubscription(subscription);
  }

  public List<SubscriptionDto> getAllSubscriptions() {
    return subscriptionRepository.findAll().stream()
            .map(this::toDtoSubscription)
            .collect(Collectors.toList());
  }

  public SubscriptionDto createSubscription(SubscriptionDto subscriptionDto) {
    if (subscriptionDto.clientId() == null) {
      throw new IllegalArgumentException("Client ID is required");
    }
    if (subscriptionDto.rateId() == null) {
      throw new IllegalArgumentException("Rate ID is required");
    }
    if (subscriptionDto.startDate() == null) {
      throw new IllegalArgumentException("Start date is required");
    }
    if (subscriptionDto.endDate() == null) {
      throw new IllegalArgumentException("End date is required");
    }

    if (!subscriptionDto.endDate().isAfter(subscriptionDto.startDate())) {
      throw new IllegalArgumentException("End date must be after start date");
    }

    ClientEntity client = clientRepository.findById(subscriptionDto.clientId())
            .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + subscriptionDto.clientId()));

    RateEntity rate = rateRepository.findById(subscriptionDto.rateId())
            .orElseThrow(() -> new IllegalArgumentException("Rate not found with id: " + subscriptionDto.rateId()));

    SubscriptionEntity subscription = new SubscriptionEntity();
    subscription.setClient(client);
    subscription.setRate(rate);
    subscription.setSubscription_startDate(subscriptionDto.startDate());
    subscription.setSubscription_endDate(subscriptionDto.endDate());
    subscription.setSubscription_freezePeriod(subscriptionDto.freezePeriod());

    SubscriptionStatus status = subscriptionDto.subscriptionStatus() != null ?
            SubscriptionStatus.valueOf(subscriptionDto.subscriptionStatus()) : SubscriptionStatus.ACTIVE;
    subscription.setSubscription_status(status);

    SubscriptionEntity saved = subscriptionRepository.save(subscription);
    return toDtoSubscription(saved);
  }

  public SubscriptionDto updateSubscription(Long id, SubscriptionDto subscriptionDto) {
    SubscriptionEntity subscription = subscriptionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Subscription not found with id: " + id));

    if (subscriptionDto.clientId() != null) {
      ClientEntity client = clientRepository.findById(subscriptionDto.clientId())
              .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + subscriptionDto.clientId()));
      subscription.setClient(client);
    }

    if (subscriptionDto.rateId() != null) {
      RateEntity rate = rateRepository.findById(subscriptionDto.rateId())
              .orElseThrow(() -> new IllegalArgumentException("Rate not found with id: " + subscriptionDto.rateId()));
      subscription.setRate(rate);
    }

    if (subscriptionDto.startDate() != null) {
      subscription.setSubscription_startDate(subscriptionDto.startDate());
    }

    if (subscriptionDto.endDate() != null) {
      LocalDate startDate = subscriptionDto.startDate() != null ?
              subscriptionDto.startDate() : subscription.getSubscription_startDate();
      if (!subscriptionDto.endDate().isAfter(startDate)) {
        throw new IllegalArgumentException("End date must be after start date");
      }
      subscription.setSubscription_endDate(subscriptionDto.endDate());
    }

    if (subscriptionDto.freezePeriod() != null) {
      subscription.setSubscription_freezePeriod(subscriptionDto.freezePeriod());
    }

    if (subscriptionDto.subscriptionStatus() != null) {
      SubscriptionStatus status = SubscriptionStatus.valueOf(subscriptionDto.subscriptionStatus());
      subscription.setSubscription_status(status);
    }

    SubscriptionEntity updated = subscriptionRepository.save(subscription);
    return toDtoSubscription(updated);
  }

  public void deleteSubscription(Long id) {
    if (!subscriptionRepository.existsById(id)) {
      throw new NoSuchElementException("Subscription not found with id: " + id);
    }
    subscriptionRepository.deleteById(id);
  }

  private SubscriptionDto toDtoSubscription(SubscriptionEntity subscription) {
    Long clientId = subscription.getClient() != null ? subscription.getClient().getClient_id() : null;
    Long rateId = subscription.getRate() != null ? subscription.getRate().getRate_id() : null;
    String status = subscription.getSubscription_status() != null ?
            subscription.getSubscription_status().name() : null;

    return new SubscriptionDto(
            subscription.getSubscription_id(),
            clientId,
            rateId,
            subscription.getSubscription_startDate(),
            subscription.getSubscription_endDate(),
            subscription.getSubscription_freezePeriod(),
            status
    );
  }
}