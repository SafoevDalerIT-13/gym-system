package ru.safoev.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safoev.dtorecords.EmployeeDto;
import ru.safoev.services.EmployeeService;

import java.util.List;

/**
 * REST контроллер для управления сотрудниками фитнес-центра.
 * <p>
 * Предоставляет CRUD операции для работы с сотрудниками через REST API.
 * Все методы работают с сущностью {@link EmployeeDto} для передачи данных.
 * </p>
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 * @see EmployeeDto
 * @see EmployeeService
 */
@RestController
@RequestMapping("/gym/employee")
public class EmployeeController {
  private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

  private final EmployeeService employeeService;

  /**
   * Конструктор контроллера с внедрением зависимости сервиса сотрудников.
   *
   * @param employeeService сервис для работы с сотрудниками
   */
  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  /**
   * Получает сотрудника по его идентификатору.
   *
   * @param id идентификатор сотрудника (обязательный)
   * @return {@link ResponseEntity} с объектом {@link EmployeeDto} и статусом 200 OK
   * @throws java.util.NoSuchElementException если сотрудник с указанным ID не найден
   * @see EmployeeService#getEmployeeById(Long)
   */
  @GetMapping("/get/{id}")
  public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id) {
    log.info("Called getEmployeeById with id: {}", id);
    return ResponseEntity.ok(employeeService.getEmployeeById(id));
  }

  /**
   * Получает список всех сотрудников.
   *
   * @return {@link ResponseEntity} со списком {@link EmployeeDto} и статусом 200 OK
   * @see EmployeeService#getAllEmployees()
   */
  @GetMapping("/get/all")
  public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
    log.info("Called getAllEmployees");
    return ResponseEntity.ok(employeeService.getAllEmployees());
  }

  /**
   * Создает нового сотрудника.
   *
   * @param employeeDto объект {@link EmployeeDto} с данными нового сотрудника (обязательный, валидируется)
   * @return {@link ResponseEntity} с созданным объектом {@link EmployeeDto} и статусом 201 CREATED
   * @throws jakarta.validation.ConstraintViolationException если данные не проходят валидацию
   * @see EmployeeService#createEmployees(EmployeeDto)
   */
  @PostMapping("/create")
  public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
    log.info("Called create Employee");
    return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployees(employeeDto));
  }

  /**
   * Обновляет данные существующего сотрудника.
   *
   * @param id идентификатор сотрудника для обновления (обязательный)
   * @param employeeDto объект {@link EmployeeDto} с новыми данными сотрудника (обязательный)
   * @return {@link ResponseEntity} с обновленным объектом {@link EmployeeDto} и статусом 200 OK
   * @throws java.util.NoSuchElementException если сотрудник с указанным ID не найден
   * @see EmployeeService#updateEmployee(Long, EmployeeDto)
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id, EmployeeDto employeeDto) {
    log.info("Called updateEmployee with id: {}", id);
    return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDto));
  }

  /**
   * Удаляет сотрудника по его идентификатору.
   *
   * @param id идентификатор сотрудника для удаления (обязательный)
   * @throws java.util.NoSuchElementException если сотрудник с указанным ID не найден
   * @see EmployeeService#deleteEmployee(Long)
   * @apiNote Метод не возвращает тело ответа, только HTTP статус
   */
  @DeleteMapping("/delete/{id}")
  public void deleteEmployee(@PathVariable("id") Long id) {
    log.info("Called deleteEmployee with id: {}", id);
    employeeService.deleteEmployee(id);
  }
}