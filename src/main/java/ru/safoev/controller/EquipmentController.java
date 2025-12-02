package ru.safoev.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safoev.dtorecords.EquipmentDto;
import ru.safoev.services.EquipmentService;

import java.util.List;

/**
 * REST контроллер для управления оборудованием фитнес-центра.
 * <p>
 * Предоставляет CRUD операции для работы с оборудованием через REST API.
 * Все методы работают с сущностью {@link EquipmentDto} для передачи данных.
 * </p>
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 * @see EquipmentDto
 * @see EquipmentService
 */
@RestController
@RequestMapping("/gym/equipment")
public class EquipmentController {
  private static final Logger log = LoggerFactory.getLogger(EquipmentController.class);

  private final EquipmentService equipmentService;

  /**
   * Конструктор контроллера с внедрением зависимости сервиса оборудования.
   *
   * @param equipmentService сервис для работы с оборудованием
   */
  @Autowired
  public EquipmentController(EquipmentService equipmentService) {
    this.equipmentService = equipmentService;
  }

  /**
   * Получает оборудование по его идентификатору.
   *
   * @param id идентификатор оборудования (обязательный)
   * @return {@link ResponseEntity} с объектом {@link EquipmentDto} и статусом 200 OK
   * @throws java.util.NoSuchElementException если оборудование с указанным ID не найден
   * @see EquipmentService#getEquipmentById(Long)
   */
  @GetMapping("/get/{id}")
  public ResponseEntity<EquipmentDto> getEquipmentById(@PathVariable("id") Long id) {
    log.info("Called getEquipmentById with id: {}", id);
    return ResponseEntity.ok(equipmentService.getEquipmentById(id));
  }

  /**
   * Получает список всего оборудования.
   *
   * @return {@link ResponseEntity} со списком {@link EquipmentDto} и статусом 200 OK
   * @see EquipmentService#getAllEquipment()
   */
  @GetMapping("/get/all")
  public ResponseEntity<List<EquipmentDto>> getAllEquipment() {
    log.info("Called getAllEquipment");
    return ResponseEntity.ok(equipmentService.getAllEquipment());
  }

  /**
   * Создает новое оборудование.
   *
   * @param equipmentDto объект {@link EquipmentDto} с данными нового оборудования (обязательный, валидируется)
   * @return {@link ResponseEntity} с созданным объектом {@link EquipmentDto} и статусом 201 CREATED
   * @throws jakarta.validation.ConstraintViolationException если данные не проходят валидацию
   * @throws java.util.NoSuchElementException если указанный зал (gymId) не найден
   * @see EquipmentService#createEquipment(EquipmentDto)
   */
  @PostMapping("/create")
  public ResponseEntity<EquipmentDto> createEquipment(@Valid @RequestBody EquipmentDto equipmentDto) {
    log.info("Called createEquipment");
    return ResponseEntity.status(HttpStatus.CREATED).body(equipmentService.createEquipment(equipmentDto));
  }

  /**
   * Обновляет данные существующего оборудования.
   *
   * @param id идентификатор оборудования для обновления (обязательный)
   * @param equipmentDto объект {@link EquipmentDto} с новыми данными оборудования (обязательный)
   * @return {@link ResponseEntity} с обновленным объектом {@link EquipmentDto} и статусом 200 OK
   * @throws java.util.NoSuchElementException если оборудование с указанным ID не найден
   * @throws java.util.NoSuchElementException если указанный зал (gymId) не найден
   * @see EquipmentService#updateEquipment(Long, EquipmentDto)
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<EquipmentDto> updateEquipment(@PathVariable("id") Long id, EquipmentDto equipmentDto) {
    log.info("Called updateEquipment with id: {}", id);
    return ResponseEntity.ok(equipmentService.updateEquipment(id, equipmentDto));
  }

  /**
   * Удаляет оборудование по его идентификатору.
   *
   * @param id идентификатор оборудования для удаления (обязательный)
   * @throws java.util.NoSuchElementException если оборудование с указанным ID не найден
   * @see EquipmentService#deleteEquipment(Long)
   * @apiNote Метод не возвращает тело ответа, только HTTP статус
   */
  @DeleteMapping("/delete/{id}")
  public void deleteEquipment(@PathVariable("id") Long id) {
    log.info("Called deleteEquipment with id: {}", id);
    equipmentService.deleteEquipment(id);
  }
}