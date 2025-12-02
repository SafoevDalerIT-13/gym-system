package ru.safoev.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safoev.dtorecords.VisitDto;
import ru.safoev.services.VisitService;

import java.util.List;

/**
 * REST контроллер для управления посещениями фитнес-центра.
 * <p>
 * Предоставляет CRUD операции для работы с посещениями через REST API.
 * Все методы работают с сущностью {@link VisitDto} для передачи данных.
 * </p>
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 * @see VisitDto
 * @see VisitService
 */
@RestController
@RequestMapping("/gym/visit")
public class VisitController {
  private static final Logger log = LoggerFactory.getLogger(VisitController.class);

  private final VisitService visitService;

  /**
   * Конструктор контроллера с внедрением зависимости сервиса посещений.
   *
   * @param visitService сервис для работы с посещениями
   */
  @Autowired
  public VisitController(VisitService visitService) {
    this.visitService = visitService;
  }

  /**
   * Получает посещение по его идентификатору.
   *
   * @param id идентификатор посещения (обязательный)
   * @return {@link ResponseEntity} с объектом {@link VisitDto} и статусом 200 OK
   * @throws java.util.NoSuchElementException если посещение с указанным ID не найден
   * @see VisitService#getVisitById(Long)
   */
  @GetMapping("/get/{id}")
  public ResponseEntity<VisitDto> getVisitById(@PathVariable("id") Long id) {
    log.info("Called getVisitById with id: {}", id);
    return ResponseEntity.ok(visitService.getVisitById(id));
  }

  /**
   * Получает список всех посещений.
   *
   * @return {@link ResponseEntity} со списком {@link VisitDto} и статусом 200 OK
   * @see VisitService#getAllVisits()
   */
  @GetMapping("/get/all")
  public ResponseEntity<List<VisitDto>> getAllVisits() {
    log.info("Called getAllVisits");
    return ResponseEntity.ok(visitService.getAllVisits());
  }

  /**
   * Создает новое посещение.
   *
   * @param visitDto объект {@link VisitDto} с данными нового посещения (обязательный, валидируется)
   * @return {@link ResponseEntity} с созданным объектом {@link VisitDto} и статусом 201 CREATED
   * @throws jakarta.validation.ConstraintViolationException если данные не проходят валидацию
   * @throws java.util.NoSuchElementException если указанные клиент (clientId) или зал (gymId) не найдены
   * @see VisitService#createVisit(VisitDto)
   */
  @PostMapping("/create")
  public ResponseEntity<VisitDto> createVisit(@Valid @RequestBody VisitDto visitDto) {
    log.info("Called createVisit with data: {}", visitDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(visitService.createVisit(visitDto));
  }

  /**
   * Обновляет данные существующего посещения.
   *
   * @param id идентификатор посещения для обновления (обязательный)
   * @param visitDto объект {@link VisitDto} с новыми данными посещения (обязательный, валидируется)
   * @return {@link ResponseEntity} с обновленным объектом {@link VisitDto} и статусом 200 OK
   * @throws java.util.NoSuchElementException если посещение с указанным ID не найден
   * @throws java.util.NoSuchElementException если указанные клиент (clientId) или зал (gymId) не найдены
   * @see VisitService#updateVisit(Long, VisitDto)
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<VisitDto> updateVisit(@PathVariable("id") Long id, @Valid @RequestBody VisitDto visitDto) {
    log.info("Called updateVisit with id: {} and data: {}", id, visitDto);
    return ResponseEntity.ok(visitService.updateVisit(id, visitDto));
  }

  /**
   * Удаляет посещение по его идентификатору.
   *
   * @param id идентификатор посещения для удаления (обязательный)
   * @return {@link ResponseEntity} без содержимого со статусом 204 NO CONTENT
   * @throws java.util.NoSuchElementException если посещение с указанным ID не найден
   * @see VisitService#deleteVisit(Long)
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteVisit(@PathVariable("id") Long id) {
    log.info("Called deleteVisit with id: {}", id);
    visitService.deleteVisit(id);
    return ResponseEntity.noContent().build();
  }
}