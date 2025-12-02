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

/**
 * REST контроллер для управления тарифами фитнес-центра.
 * <p>
 * Предоставляет CRUD операции для работы с тарифами через REST API.
 * Все методы работают с сущностью {@link RateDto} для передачи данных.
 * </p>
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 * @see RateDto
 * @see RateService
 */
@RestController
@RequestMapping("/gym/rate")
public class RateController {
  private static final Logger log = LoggerFactory.getLogger(RateController.class);

  private final RateService rateService;

  /**
   * Конструктор контроллера с внедрением зависимости сервиса тарифов.
   *
   * @param rateService сервис для работы с тарифами
   */
  @Autowired
  public RateController(RateService rateService) {
    this.rateService = rateService;
  }

  /**
   * Получает тариф по его идентификатору.
   *
   * @param id идентификатор тарифа (обязательный)
   * @return {@link ResponseEntity} с объектом {@link RateDto} и статусом 200 OK
   * @throws java.util.NoSuchElementException если тариф с указанным ID не найден
   * @see RateService#getRateById(Long)
   */
  @GetMapping("/get/{id}")
  public ResponseEntity<RateDto> getRateById(@PathVariable("id") Long id) {
    log.info("Called getRateById with id: {}", id);
    return ResponseEntity.ok(rateService.getRateById(id));
  }

  /**
   * Получает список всех тарифов.
   *
   * @return {@link ResponseEntity} со списком {@link RateDto} и статусом 200 OK
   * @see RateService#getAllRates()
   */
  @GetMapping("/get/all")
  public ResponseEntity<List<RateDto>> getAllRates() {
    log.info("Called getAllRates");
    return ResponseEntity.ok(rateService.getAllRates());
  }

  /**
   * Создает новый тариф.
   *
   * @param rateDto объект {@link RateDto} с данными нового тарифа (обязательный, валидируется)
   * @return {@link ResponseEntity} с созданным объектом {@link RateDto} и статусом 201 CREATED
   * @throws jakarta.validation.ConstraintViolationException если данные не проходят валидацию
   * @see RateService#createRate(RateDto)
   */
  @PostMapping("/create")
  public ResponseEntity<RateDto> createRate(@Valid @RequestBody RateDto rateDto) {
    log.info("Called createRate with data: {}", rateDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(rateService.createRate(rateDto));
  }

  /**
   * Обновляет данные существующего тарифа.
   *
   * @param id идентификатор тарифа для обновления (обязательный)
   * @param rateDto объект {@link RateDto} с новыми данными тарифа (обязательный, валидируется)
   * @return {@link ResponseEntity} с обновленным объектом {@link RateDto} и статусом 200 OK
   * @throws java.util.NoSuchElementException если тариф с указанным ID не найден
   * @throws jakarta.validation.ConstraintViolationException если данные не проходят валидацию
   * @see RateService#updateRate(Long, RateDto)
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<RateDto> updateRate(@PathVariable("id") Long id, @Valid @RequestBody RateDto rateDto) {
    log.info("Called updateRate with id: {} and data: {}", id, rateDto);
    return ResponseEntity.ok(rateService.updateRate(id, rateDto));
  }

  /**
   * Удаляет тариф по его идентификатору.
   *
   * @param id идентификатор тарифа для удаления (обязательный)
   * @return {@link ResponseEntity} без содержимого со статусом 204 NO CONTENT
   * @throws java.util.NoSuchElementException если тариф с указанным ID не найден
   * @see RateService#deleteRate(Long)
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteRate(@PathVariable("id") Long id) {
    log.info("Called deleteRate with id: {}", id);
    rateService.deleteRate(id);
    return ResponseEntity.noContent().build();
  }
}