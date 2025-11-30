package ru.safoev.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safoev.dtorecords.SubscriptionDto;
import ru.safoev.services.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping("/gym/subscription")
public class SubscriptionController {
  private static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

  private final SubscriptionService subscriptionService;

  @Autowired
  public SubscriptionController(SubscriptionService subscriptionService) {
    this.subscriptionService = subscriptionService;
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<SubscriptionDto> getSubscriptionById(@PathVariable("id") Long id) {
    log.info("Called getSubscriptionById with id: {}", id);
    return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
  }

  @GetMapping("/get/all")
  public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
    log.info("Called getAllSubscriptions");
    return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
  }

  @PostMapping("/create")
  public ResponseEntity<SubscriptionDto> createSubscription(@Valid @RequestBody SubscriptionDto subscriptionDto) {
    log.info("Called createSubscription with data: {}", subscriptionDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.createSubscription(subscriptionDto));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<SubscriptionDto> updateSubscription(@PathVariable("id") Long id, @Valid @RequestBody SubscriptionDto subscriptionDto) {
    log.info("Called updateSubscription with id: {} and data: {}", id, subscriptionDto);
    return ResponseEntity.ok(subscriptionService.updateSubscription(id, subscriptionDto));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteSubscription(@PathVariable("id") Long id) {
    log.info("Called deleteSubscription with id: {}", id);
    subscriptionService.deleteSubscription(id);
    return ResponseEntity.noContent().build();
  }
}