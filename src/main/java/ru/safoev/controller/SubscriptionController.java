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

/**
 * REST контроллер для управления абонементами фитнес-центра.
 * <p>
 * Предоставляет CRUD операции для работы с абонементами через REST API.
 * Все методы работают с сущностью {@link SubscriptionDto} для передачи данных.
 * </p>
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 * @see SubscriptionDto
 * @see SubscriptionService
 */
@RestController
@RequestMapping("/gym/subscription")
public class SubscriptionController {
  private static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

  private final SubscriptionService subscriptionService;

  /**
   * Конструктор контроллера с внедрением зависимости сервиса абонементов.
   *
   * @param subscriptionService сервис для работы с абонементами
   */
  @Autowired
  public SubscriptionController(SubscriptionService subscriptionService) {
    this.subscriptionService = subscriptionService;
  }

  /**
   * Получает абонемент по его идентификатору.
   *
   * @param id идентификатор абонемента (обязательный)
   * @return {@link ResponseEntity} с объектом {@link SubscriptionDto} и статусом 200 OK
   * @throws java.util.NoSuchElementException если абонемент с указанным ID не найден
   * @see SubscriptionService#getSubscriptionById(Long)
   */
  @GetMapping("/get/{id}")
  public ResponseEntity<SubscriptionDto> getSubscriptionById(@PathVariable("id") Long id) {
    log.info("Called getSubscriptionById with id: {}", id);
    return ResponseEntity.ok(subscriptionService.getSubscriptionById(id));
  }

  /**
   * Получает список всех абонементов.
   *
   * @return {@link ResponseEntity} со списком {@link SubscriptionDto} и статусом 200 OK
   * @see SubscriptionService#getAllSubscriptions()
   */
  @GetMapping("/get/all")
  public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
    log.info("Called getAllSubscriptions");
    return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
  }

  /**
   * Создает новый абонемент.
   *
   * @param subscriptionDto объект {@link SubscriptionDto} с данными нового абонемента (обязательный, валидируется)
   * @return {@link ResponseEntity} с созданным объектом {@link SubscriptionDto} и статусом 201 CREATED
   * @throws jakarta.validation.ConstraintViolationException если данные не проходят валидацию
   * @throws java.util.NoSuchElementException если указанный клиент (clientId) или тариф (rateId) не найден
   * @throws IllegalArgumentException если дата окончания не позже даты начала
   * @see SubscriptionService#createSubscription(SubscriptionDto)
   */
  @PostMapping("/create")
  public ResponseEntity<SubscriptionDto> createSubscription(@Valid @RequestBody SubscriptionDto subscriptionDto) {
    log.info("Called createSubscription with data: {}", subscriptionDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.createSubscription(subscriptionDto));
  }

  /**
   * Обновляет данные существующего абонемента.
   *
   * @param id идентификатор абонемента для обновления (обязательный)
   * @param subscriptionDto объект {@link SubscriptionDto} с новыми данными абонемента (обязательный, валидируется)
   * @return {@link ResponseEntity} с обновленным объектом {@link SubscriptionDto} и статусом 200 OK
   * @throws java.util.NoSuchElementException если абонемент с указанным ID не найден
   * @throws java.util.NoSuchElementException если указанные клиент (clientId) или тариф (rateId) не найдены
   * @throws IllegalArgumentException если дата окончания не позже даты начала
   * @see SubscriptionService#updateSubscription(Long, SubscriptionDto)
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<SubscriptionDto> updateSubscription(@PathVariable("id") Long id, @Valid @RequestBody SubscriptionDto subscriptionDto) {
    log.info("Called updateSubscription with id: {} and data: {}", id, subscriptionDto);
    return ResponseEntity.ok(subscriptionService.updateSubscription(id, subscriptionDto));
  }

  /**
   * Удаляет абонемент по его идентификатору.
   *
   * @param id идентификатор абонемента для удаления (обязательный)
   * @return {@link ResponseEntity} без содержимого со статусом 204 NO CONTENT
   * @throws java.util.NoSuchElementException если абонемент с указанным ID не найден
   * @see SubscriptionService#deleteSubscription(Long)
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteSubscription(@PathVariable("id") Long id) {
    log.info("Called deleteSubscription with id: {}", id);
    subscriptionService.deleteSubscription(id);
    return ResponseEntity.noContent().build();
  }
}