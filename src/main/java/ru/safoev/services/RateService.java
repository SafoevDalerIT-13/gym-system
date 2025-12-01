package ru.safoev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.safoev.dtorecords.RateDto;
import ru.safoev.entity.RateEntity;
import ru.safoev.mappers.RateMapper;
import ru.safoev.repositoryinterface.RateRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RateService {

  private final RateRepository rateRepository;
  private final RateMapper rateMapper;

  @Autowired
  public RateService(RateRepository rateRepository, RateMapper rateMapper) {
    this.rateRepository = rateRepository;
    this.rateMapper = rateMapper;
  }

  public RateDto getRateById(Long id) {
    RateEntity rate = rateRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Rate not found with id: " + id));
    return rateMapper.toDto(rate);
  }

  public List<RateDto> getAllRates() {
    return rateRepository.findAll().stream()
            .map(rateMapper::toDto)
            .collect(Collectors.toList());
  }

  public RateDto createRate(RateDto rateDto) {
    RateEntity entityToSave = rateMapper.toEntity(rateDto);
    RateEntity saved = rateRepository.save(entityToSave);
    return rateMapper.toDto(saved);
  }

  public RateDto updateRate(Long id, RateDto rateDto) {
    RateEntity existingRate = rateRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Rate not found with id: " + id));

    rateMapper.updateEntityFromDto(rateDto, existingRate);

    RateEntity updated = rateRepository.save(existingRate);
    return rateMapper.toDto(updated);
  }

  public void deleteRate(Long id) {
    if (!rateRepository.existsById(id)) {
      throw new NoSuchElementException("Rate not found with id: " + id);
    }
    rateRepository.deleteById(id);
  }
}