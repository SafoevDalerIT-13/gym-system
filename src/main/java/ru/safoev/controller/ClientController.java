package ru.safoev.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.safoev.dtorecords.ClientDto;
import ru.safoev.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("/gym/client")
public class ClientController {

  private static final Logger log = LoggerFactory.getLogger(ClientController.class);

  private final ClientService clientService;

  @Autowired
  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<ClientDto> getClientById(@PathVariable("id") Long id) {
    log.info("Called getClientById with id: {}", id);
    return ResponseEntity.ok(clientService.getClientById(id));
  }

  @GetMapping("/get/all")
  public ResponseEntity<List<ClientDto>> getAllClients() {
    log.info("Called getAllClients");
    return ResponseEntity.ok(clientService.getAllClients());
  }

  @PostMapping("/create")
  public ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientDto clientDto) {
    log.info("Called createClient");
    return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientDto));
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<ClientDto> updateClient(@PathVariable("id") Long id, @Valid @RequestBody ClientDto clientDto) {
    log.info("Called updateClient with id: {}", id);
    return ResponseEntity.ok(clientService.updateClient(id, clientDto));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ClientDto> deleteClient(@PathVariable("id") Long id) {
    log.info("Called deleteClient with id: {}", id);
    clientService.deleteClient(id);
    return ResponseEntity.noContent().build();
  }
}