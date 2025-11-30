package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safoev.dtorecords.RateDto;
import ru.safoev.entity.RateEntity;
import ru.safoev.repositoryinterface.RateRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RateService {

  private final RateRepository rateRepository;

  @Autowired
  public RateService(RateRepository rateRepository) {
    this.rateRepository = rateRepository;
  }

  public RateDto getRateById(Long id) {
    RateEntity rate = rateRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Rate not found with id: " + id));
    return toDtoRate(rate);
  }

  public List<RateDto> getAllRates() {
    return rateRepository.findAll().stream()
            .map(this::toDtoRate)
            .collect(Collectors.toList());
  }

  public RateDto createRate(RateDto rateDto) {
    if (rateDto.rateName() == null || rateDto.rateName().isBlank()) {
      throw new IllegalArgumentException("Rate name is required");
    }
    if (rateDto.price() == null || rateDto.price().compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Price must be positive");
    }
    if (rateDto.durationDays() == null || rateDto.durationDays() <= 0) {
      throw new IllegalArgumentException("Duration days must be positive");
    }

    RateEntity rate = new RateEntity();
    rate.setRate_name(rateDto.rateName());
    rate.setRate_price(rateDto.price());
    rate.setRate_price_period(rateDto.pricePeriod());
    rate.setRate_duration_days(rateDto.durationDays());
    rate.setRate_description(rateDto.description());

    RateEntity saved = rateRepository.save(rate);
    return toDtoRate(saved);
  }

  public RateDto updateRate(Long id, RateDto rateDto) {
    RateEntity rate = rateRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Rate not found with id: " + id));

    if (rateDto.rateName() != null) {
      rate.setRate_name(rateDto.rateName());
    }
    if (rateDto.price() != null && rateDto.price().compareTo(BigDecimal.ZERO) > 0) {
      rate.setRate_price(rateDto.price());
    }
    if (rateDto.pricePeriod() != null) {
      rate.setRate_price_period(rateDto.pricePeriod());
    }
    if (rateDto.durationDays() != null && rateDto.durationDays() > 0) {
      rate.setRate_duration_days(rateDto.durationDays());
    }
    if (rateDto.description() != null) {
      rate.setRate_description(rateDto.description());
    }

    RateEntity updated = rateRepository.save(rate);
    return toDtoRate(updated);
  }

  public void deleteRate(Long id) {
    if (!rateRepository.existsById(id)) {
      throw new NoSuchElementException("Rate not found with id: " + id);
    }
    rateRepository.deleteById(id);
  }


  private RateDto toDtoRate(RateEntity rate) {
    return new RateDto(
            rate.getRate_id(),
            rate.getRate_name(),
            rate.getRate_price(),
            rate.getRate_price_period(),
            rate.getRate_duration_days(),
            rate.getRate_description()
    );
  }
}