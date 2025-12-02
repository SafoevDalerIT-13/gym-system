package ru.safoev.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safoev.dtorecords.GymDto;
import ru.safoev.services.GymService;

import java.util.List;

/**
 * REST контроллер для управления залами (фитнес-центрами).
 * <p>
 * Предоставляет CRUD операции для работы с залами через REST API.
 * Все методы работают с сущностью {@link GymDto} для передачи данных.
 * </p>
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 * @see GymDto
 * @see GymService
 */
@RestController
@RequestMapping("/gym")
public class GymController_Home {
  private static final Logger log = LoggerFactory.getLogger(GymController_Home.class);

  private final GymService gymService;

  /**
   * Конструктор контроллера с внедрением зависимости сервиса залов.
   *
   * @param gymService сервис для работы с залами
   */
  @Autowired
  public GymController_Home(GymService gymService) {
    this.gymService = gymService;
  }

  /**
   * Получает зал по его идентификатору.
   *
   * @param id идентификатор зала (обязательный)
   * @return {@link ResponseEntity} с объектом {@link GymDto} и статусом 200 OK
   * @throws java.util.NoSuchElementException если зал с указанным ID не найден
   * @see GymService#getGymById(Long)
   */
  @GetMapping("/get/{id}")
  public ResponseEntity<GymDto> getGymById(@PathVariable("id") Long id) {
    log.info("Called getGymById with id: {}", id);
    return ResponseEntity.ok(gymService.getGymById(id));
  }

  /**
   * Получает список всех залов.
   *
   * @return {@link ResponseEntity} со списком {@link GymDto} и статусом 200 OK
   * @see GymService#getAllGym()
   */
  @GetMapping("/get/all")
  public ResponseEntity<List<GymDto>> getAllGyms() {
    log.info("Called getAllGyms");
    return ResponseEntity.ok(gymService.getAllGym());
  }

  /**
   * Создает новый зал.
   *
   * @param gymDto объект {@link GymDto} с данными нового зала (обязательный, валидируется)
   * @return {@link ResponseEntity} с созданным объектом {@link GymDto} и статусом 201 CREATED
   * @throws jakarta.validation.ConstraintViolationException если данные не проходят валидацию
   * @throws IllegalArgumentException если время закрытия не позже времени открытия
   * @see GymService#createGym(GymDto)
   */
  @PostMapping("/create")
  public ResponseEntity<GymDto> createGym(@Valid @RequestBody GymDto gymDto) {
    log.info("Called createGym");
    return ResponseEntity.status(HttpStatus.CREATED).body(gymService.createGym(gymDto));
  }

  /**
   * Обновляет данные существующего зала.
   *
   * @param id идентификатор зала для обновления (обязательный)
   * @param gymDto объект {@link GymDto} с новыми данными зала (обязательный, валидируется)
   * @return {@link ResponseEntity} с обновленным объектом {@link GymDto} и статусом 200 OK
   * @throws java.util.NoSuchElementException если зал с указанным ID не найден
   * @throws IllegalArgumentException если время закрытия не позже времени открытия
   * @see GymService#updateGym(Long, GymDto)
   * @apiNote Отсутствует аннотация {@code @RequestBody} для параметра gymDto
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<GymDto> updateGym(@PathVariable("id") Long id, @Valid GymDto gymDto) {
    log.info("Called updateGym with id: {}", id);
    return ResponseEntity.ok(gymService.updateGym(id, gymDto));
  }

  /**
   * Удаляет зал по его идентификатору.
   *
   * @param id идентификатор зала для удаления (обязательный)
   * @return {@link ResponseEntity} без содержимого со статусом 204 NO CONTENT
   * @throws java.util.NoSuchElementException если зал с указанным ID не найден
   * @see GymService#deleteGym(Long)
   * @apiNote В лог сообщении опечатка "updateGym", должно быть "deleteGym"
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteGym(@PathVariable("id") Long id) {
    log.info("Called deleteGym with id: {}", id);
    gymService.deleteGym(id);
    return ResponseEntity.noContent().build();
  }
}