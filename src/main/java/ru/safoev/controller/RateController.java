package ru.safoev.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safoev.dtorecords.RateDto;
import ru.safoev.services.RateService;

import java.util.List;

@RestController
@RequestMapping("/gym/rate")
public class RateController {
  private static final Logger log = LoggerFactory.getLogger(RateController.class);

  private final RateService rateService;

  @Autowired
  public RateController(RateService rateService) {
    this.rateService = rateService;
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<RateDto> getRateById(@PathVariable("id") Long id) {
    log.info("Called getRateById with id: {}", id);
    return ResponseEntity.ok(rateService.getRateById(id));
  }

  @GetMapping("/get/all")
  public ResponseEntity<List<RateDto>> getAllRates() {
    log.info("Called getAllRates");
    return ResponseEntity.ok(rateService.getAllRates());
  }

  @PostMapping("/create")
  public ResponseEntity<RateDto> createRate(@Valid @RequestBody RateDto rateDto) {
    log.info("Called createRate with data: {}", rateDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(rateService.createRate(rateDto));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<RateDto> updateRate(@PathVariable("id") Long id, @Valid @RequestBody RateDto rateDto) {
    log.info("Called updateRate with id: {} and data: {}", id, rateDto);
    return ResponseEntity.ok(rateService.updateRate(id, rateDto));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteRate(@PathVariable("id") Long id) {
    log.info("Called deleteRate with id: {}", id);
    rateService.deleteRate(id);
    return ResponseEntity.noContent().build();
  }
}