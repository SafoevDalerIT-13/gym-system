package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safoev.dtorecords.SubscriptionDto;
import ru.safoev.entity.SubscriptionEntity;
import ru.safoev.entity.ClientEntity;
import ru.safoev.entity.RateEntity;
import ru.safoev.mappers.SubscriptionMapper;
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
  private final SubscriptionMapper subscriptionMapper;

  @Autowired
  public SubscriptionService(SubscriptionRepository subscriptionRepository,
                             ClientRepository clientRepository,
                             RateRepository rateRepository, SubscriptionMapper subscriptionMapper) {
    this.subscriptionRepository = subscriptionRepository;
    this.clientRepository = clientRepository;
    this.rateRepository = rateRepository;
    this.subscriptionMapper = subscriptionMapper;
  }

  public SubscriptionDto getSubscriptionById(Long id) {
    SubscriptionEntity subscription = subscriptionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Subscription not found with id: " + id));
    return subscriptionMapper.toDto(subscription);
  }

  public List<SubscriptionDto> getAllSubscriptions() {
    return subscriptionRepository.findAll().stream()
            .map(subscriptionMapper::toDto)
            .collect(Collectors.toList());
  }

  public SubscriptionDto createSubscription(SubscriptionDto subscriptionDto) {
    ClientEntity client = clientRepository.findById(subscriptionDto.clientId())
            .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + subscriptionDto.clientId()));

    RateEntity rate = rateRepository.findById(subscriptionDto.rateId())
            .orElseThrow(() -> new IllegalArgumentException("Rate not found with id: " + subscriptionDto.rateId()));

    SubscriptionEntity subscription = subscriptionMapper.toEntity(subscriptionDto, client, rate);

    SubscriptionEntity saved = subscriptionRepository.save(subscription);
    return subscriptionMapper.toDto(saved);
  }

  public SubscriptionDto updateSubscription(Long id, SubscriptionDto subscriptionDto) {
    SubscriptionEntity existingSubscription = subscriptionRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Subscription not found with id: " + id));

    ClientEntity client = null;
    if (subscriptionDto.clientId() != null) {
      client = clientRepository.findById(subscriptionDto.clientId())
              .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + subscriptionDto.clientId()));
    }

    RateEntity rate = null;
    if (subscriptionDto.rateId() != null) {
      rate = rateRepository.findById(subscriptionDto.rateId())
              .orElseThrow(() -> new IllegalArgumentException("Rate not found with id: " + subscriptionDto.rateId()));
    }

    if (subscriptionDto.endDate() != null) {
      LocalDate startDate = subscriptionDto.startDate() != null ?
              subscriptionDto.startDate() : existingSubscription.getSubscription_startDate();
      if (!subscriptionDto.endDate().isAfter(startDate)) {
        throw new IllegalArgumentException("End date must be after start date");
      }
    }
    subscriptionMapper.updateEntityFromDto(subscriptionDto, existingSubscription, client, rate);

    SubscriptionEntity updated = subscriptionRepository.save(existingSubscription);
    return subscriptionMapper.toDto(updated);
  }

  public void deleteSubscription(Long id) {
    if (!subscriptionRepository.existsById(id)) {
      throw new NoSuchElementException("Subscription not found with id: " + id);
    }
    subscriptionRepository.deleteById(id);
  }
}