package ru.safoev.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safoev.dtorecords.ClientDto;
import ru.safoev.filters.ClientSearchFilter;
import ru.safoev.services.ClientService;

import java.util.List;

/**
 * REST контроллер для управления клиентами фитнес-центра.
 * <p>
 * Предоставляет CRUD операции для работы с клиентами через REST API.
 * Все методы работают с сущностью {@link ClientDto} для передачи данных.
 * </p>
 *
 * @author SafoevDalerIT-13
 * @version 1.0
 * @since 2025
 * @see ClientDto
 * @see ClientService
 * @see ClientSearchFilter
 */
@RestController
@RequestMapping("/gym/client")
public class ClientController {

  private static final Logger log = LoggerFactory.getLogger(ClientController.class);

  private final ClientService clientService;

  /**
   * Конструктор контроллера с внедрением зависимости сервиса клиентов.
   *
   * @param clientService сервис для работы с клиентами
   */
  @Autowired
  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  /**
   * Получает клиента по его идентификатору.
   *
   * @param id идентификатор клиента (обязательный)
   * @return {@link ResponseEntity} с объектом {@link ClientDto} и статусом 200 OK
   * @throws jakarta.persistence.EntityNotFoundException если клиент с указанным ID не найден
   * @see ClientService#getClientById(Long)
   */
  @GetMapping("/get/{id}")
  public ResponseEntity<ClientDto> getClientById(@PathVariable("id") Long id) {
    log.info("Called getClientById with id: {}", id);
    return ResponseEntity.ok(clientService.getClientById(id));
  }

  /**
   * Получает список всех клиентов.
   *
   * @return {@link ResponseEntity} со списком {@link ClientDto} и статусом 200 OK
   * @see ClientService#getAllClients()
   */
  @GetMapping("/get/all")
  public ResponseEntity<List<ClientDto>> getAllClients() {
    log.info("Called getAllClients");
    return ResponseEntity.ok(clientService.getAllClients());
  }

  /**
   * Выполняет поиск клиентов с использованием фильтров и пагинации.
   *
   * @param client_id идентификатор клиента для фильтрации (может быть null)
   * @param client_email email клиента для фильтрации (может быть null)
   * @param pageSize размер страницы для пагинации (положительное число)
   * @param pageNumber номер страницы для пагинации (начинается с 0)
   * @return {@link ResponseEntity} с отфильтрованным списком {@link ClientDto} и статусом 200 OK
   * @see ClientSearchFilter
   * @see ClientService#searchAllClientsByFilter(ClientSearchFilter)
   */
  @GetMapping("/search/filter")
  public ResponseEntity<List<ClientDto>> searchAllClientsByFilter(
          @RequestParam("client_id") Long client_id,
          @RequestParam("client_email") String client_email,
          @RequestParam("pageSize") Integer pageSize,
          @RequestParam("pageNumber") Integer pageNumber
  ) {
    log.info("Called getAllClientsByFilter");
    var filter = new ClientSearchFilter(client_id,client_email,pageSize,pageNumber);
    return ResponseEntity.ok(clientService.searchAllClientsByFilter(filter));
  }

  /**
   * Создает нового клиента.
   *
   * @param clientDto объект {@link ClientDto} с данными нового клиента (обязательный, валидируется)
   * @return {@link ResponseEntity} с созданным объектом {@link ClientDto} и статусом 201 CREATED
   * @throws jakarta.validation.ConstraintViolationException если данные не проходят валидацию
   * @see ClientService#createClient(ClientDto)
   */
  @PostMapping("/create")
  public ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientDto clientDto) {
    log.info("Called createClient");
    return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientDto));
  }

  /**
   * Обновляет данные существующего клиента.
   *
   * @param id идентификатор клиента для обновления (обязательный)
   * @param clientDto объект {@link ClientDto} с новыми данными клиента (обязательный, валидируется)
   * @return {@link ResponseEntity} с обновленным объектом {@link ClientDto} и статусом 200 OK
   * @throws jakarta.persistence.EntityNotFoundException если клиент с указанным ID не найден
   * @throws jakarta.validation.ConstraintViolationException если данные не проходят валидацию
   * @see ClientService#updateClient(Long, ClientDto)
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<ClientDto> updateClient(@PathVariable("id") Long id, @Valid @RequestBody ClientDto clientDto) {
    log.info("Called updateClient with id: {}", id);
    return ResponseEntity.ok(clientService.updateClient(id, clientDto));
  }

  /**
   * Удаляет клиента по его идентификатору.
   *
   * @param id идентификатор клиента для удаления (обязательный)
   * @return {@link ResponseEntity} без содержимого со статусом 204 NO CONTENT
   * @throws java.util.NoSuchElementException если клиент с указанным ID не найден
   * @see ClientService#deleteClient(Long)
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ClientDto> deleteClient(@PathVariable("id") Long id) {
    log.info("Called deleteClient with id: {}", id);
    clientService.deleteClient(id);
    return ResponseEntity.noContent().build();
  }
}